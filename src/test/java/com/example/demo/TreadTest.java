package com.example.demo;


import org.junit.Test;
import org.springframework.util.StopWatch;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class TreadTest {

//    @Test
//    public void testThread(){
//        ThreadPoolExecutor executorService = ThreadPoolExecutorContainer.take();
//        AtomicInteger atomicInteger = new AtomicInteger();
//        for(int i=0;i<100;i++){
//            executorService.submit(()->{
//                atomicInteger.getAndIncrement();
//                try {
//                    TimeUnit.SECONDS.sleep(1);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            });
//        }
//        while (executorService.getCompletedTaskCount()!=100){
//            try {
//                TimeUnit.MILLISECONDS.sleep(10);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
//        System.out.println(atomicInteger.get());
//    }

    @Test
    public void testThreadPoolExecutor1(){//699.074 1
        ThreadPoolExecutor executor = new ThreadPoolExecutor(16,16,4,TimeUnit.SECONDS,
                new SynchronousQueue<>(),
                new ThreadFactory() {
                    AtomicInteger i = new AtomicInteger(1);
                    @Override
                    public Thread newThread(Runnable r) {
                        Thread thread = new Thread(r);
                        thread.setName(i.getAndIncrement()+"th thread");
                        return thread;
                    }
                },
                new ThreadPoolExecutor.CallerRunsPolicy());
        testExecutor(executor);
    }

    @Test
    public void testThreadPoolExecutor2(){//184.054 : 1
        ThreadPoolExecutor executor = new ThreadPoolExecutor(64,64,4,TimeUnit.SECONDS,
                new SynchronousQueue<>(),
                new ThreadFactory() {
                    AtomicInteger i = new AtomicInteger(1);
                    @Override
                    public Thread newThread(Runnable r) {
                        Thread thread = new Thread(r);
                        thread.setName(i.getAndIncrement()+"th thread");
                        return thread;
                    }
                },
                new ThreadPoolExecutor.CallerRunsPolicy());
        testExecutor(executor);
    }

    @Test
    public void testThreadPoolExecutor3(){ //157.043 :  1
        ThreadPoolExecutor executor = new ThreadPoolExecutor(64,64,4,TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(),
                new ThreadFactory() {
                    AtomicInteger i = new AtomicInteger(1);
                    @Override
                    public Thread newThread(Runnable r) {
                        Thread thread = new Thread(r);
                        thread.setName(i.getAndIncrement()+"th thread");
                        return thread;
                    }
                });
        testExecutor(executor);
    }

    @Test
    public void testThreadPoolExecutor4(){//625.061 : 1
        ThreadPoolExecutor executor = new ThreadPoolExecutor(1500,1500,4,TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(),
                new ThreadFactory() {
                    AtomicInteger i = new AtomicInteger(1);
                    @Override
                    public Thread newThread(Runnable r) {
                        Thread thread = new Thread(r);
                        thread.setName(i.getAndIncrement()+"th thread");
                        return thread;
                    }
                });
        testExecutor(executor);
    }

    @Test
    public void testThreadPoolExecutor5(){//
        for(int j=1;j<=8000/300;j++){
            ThreadPoolExecutor executor = new ThreadPoolExecutor(100,j*300,4,TimeUnit.SECONDS,
                    new SynchronousQueue<>(),new ThreadPoolExecutor.CallerRunsPolicy());
            testExecutor(executor);
        }
    }

    @Test
    public void testThreadPoolExecutor6(){//
        for(int j=1;j<=8000/300;j++){
            ThreadPoolExecutor executor = new ThreadPoolExecutor(j*300,j*300,4,TimeUnit.SECONDS,
                    new LinkedBlockingQueue<>(),
                    new ThreadFactory() {
                        AtomicInteger i = new AtomicInteger(1);
                        @Override
                        public Thread newThread(Runnable r) {
                            Thread thread = new Thread(r);
                            thread.setName(i.getAndIncrement()+"th thread");
                            return thread;
                        }
                    });
            testExecutor(executor);
        }
    }

    private void testExecutor(ThreadPoolExecutor executor){
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        CountDownLatch latch = new CountDownLatch(10000);
        for (int i=0;i<10000;i++)
            executor.submit(() -> {
                try {
                    TimeUnit.MILLISECONDS.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                latch.countDown();
            });
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        stopWatch.stop();
        executor.shutdown();
        System.out.println(stopWatch.getTotalTimeSeconds());
    }


}
