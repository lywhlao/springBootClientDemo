package com.example.demo;

import org.junit.Test;

import java.util.concurrent.*;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.locks.StampedLock;
import java.util.function.Supplier;

/**
 * @Author: laojiaqi
 * @Date: 2019/6/18 23:04
 * @Description:
 */
public class SemaphoreTest {


    @Test
    public void Semaphore() throws InterruptedException {
        Semaphore semaphore=new Semaphore(1,true);
//        semaphore.tryAcquire()
        semaphore.acquire();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    semaphore.acquire();
                    System.out.println("get semaphore 1");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
//        semaphore.tryAcquire();
//        semaphore.tryAcquire(1, TimeUnit.SECONDS);
        Thread.sleep(100L);
        System.out.println("try get semaphore");
        semaphore.release();
        semaphore.acquire();
        System.out.println("hello");
//        boolean b = semaphore.tryAcquire(1);
//        if(b){
//
//        }
    }


    /**
     *
     * lock support
     * @throws InterruptedException
     */
    @Test
    public void LockSupport() throws InterruptedException {
        Object object=new Object();
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("==>park");
                LockSupport.park(object);
                System.out.println("==>unpark");
            }
        });
        thread.start();
//        Thread.sleep(1000);
        System.out.println("call upark");
        LockSupport.unpark(thread);

        Thread.sleep(100000);
    }


    @Test
    public void ReentrantLock() throws InterruptedException {
        ReentrantLock reentrantLock=new ReentrantLock(false);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    reentrantLock.lock();
                    System.out.println("get semaphore 1");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
        Thread.sleep(100L);
        reentrantLock.tryLock(1,TimeUnit.SECONDS);
        reentrantLock.unlock();;
    }


    @Test
    public void StampLock(){
        StampedLock stampedLock = new StampedLock();
        long l = stampedLock.writeLock();
        stampedLock.unlock(l);

    }


    @Test
    public void CountDownLatch() throws InterruptedException {
        CountDownLatch countDownLatch=new CountDownLatch(2);
        countDownLatch.await();
        countDownLatch.countDown();
        countDownLatch.countDown();;


    }



    @Test
    public void readWriteLock() throws InterruptedException {
        ReentrantReadWriteLock reentrantReadWriteLock=new ReentrantReadWriteLock();

        reentrantReadWriteLock.readLock().lock();




    }



    @Test
    public void TestFutureTask() throws InterruptedException, ExecutionException {
        ExecutorService executorService = Executors.newCachedThreadPool();

        FutureTask<String> futureTask=new FutureTask<>(new Callable<String>() {
            @Override
            public String call() throws Exception {
                System.out.println("in futrue task");
                return "test";
            }
        });
//        futureTask.get();
        executorService.execute(futureTask);
        Future<?> submit = executorService.submit(futureTask);
        Object o = submit.get();

        Thread t=Thread.currentThread();
        boolean interrupted = t.isInterrupted();
        System.out.println("1 ==> "+interrupted);
        try {
            t.interrupt();
        } catch (Exception e) {
            interrupted = t.isInterrupted();
            t.interrupt();
            System.out.println("2==> "+interrupted);

        }

        try {
            String s = futureTask.get();
            System.out.println(s);
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void TestArrayBlockingQueue() throws ExecutionException, InterruptedException {
        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(new Supplier<String>() {
            @Override
            public String get() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return "test";
            }
        });
        CompletableFuture<String> completableFuture1 = completableFuture.thenApply(item -> {
            System.out.println(Thread.currentThread().getName()+">>> "+item + ">>" + "test2");
            return "test2";
        });

        String s = completableFuture.get();
        System.out.println(">>>>"+s);
    }

}
