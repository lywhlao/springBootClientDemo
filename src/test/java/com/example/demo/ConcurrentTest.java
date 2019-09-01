package com.example.demo;

import org.junit.Test;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author: laojiaqi
 * @Date: 2019/6/28 22:47
 * @Description:
 */
public class ConcurrentTest {

    static int a=1;

    public static void incrA(){
        a++;
    }

    static int b=1;

    public static void incrB(){
        b++;
    }

    AtomicInteger stat=new AtomicInteger(0);

    /**
     * 没有进行同步，结果是错的
     * @throws InterruptedException
     */
    @Test
    public void normal() throws InterruptedException {
        for(int i=0;i<20;i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int j = 0; j <20000 ; j++) {
                        incrA();
                    }
                }
            }).start();
        }
        while(Thread.activeCount()>1) {
            Thread.yield();
        }
        System.out.println(a);
    }


    /**
     * 用 可重入锁， 结果正确
     * @throws InterruptedException
     */
    @Test
    public void testReentranLock() throws InterruptedException {
        ReentrantLock reentrantLock=new ReentrantLock();
        for(int i=0;i<20;i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int j = 0; j <20000 ; j++) {
                        reentrantLock.lock();
                        incrA();
                        reentrantLock.unlock();
                    }
                }
            }).start();
        }
        while(Thread.activeCount()>1) {
            Thread.yield();
        }
        System.out.println(a);
    }

    /**
     * 利用cas 结果正确
     * @throws InterruptedException
     */
    @Test
    public void testCAS() throws InterruptedException {
        for(int i=0;i<20;i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int j = 0; j <20000 ; j++) {
                        boolean success=false;
                        while (!success){
                            success=stat.compareAndSet(0,1);
                        }
                        incrA();
                        //state的值是volatile，这个操作之前的写操作对其他可见，符合heppns-before原则
                        stat.set(0);
                    }
                }
            }).start();
        }
        while(Thread.activeCount()>1) {
            Thread.yield();
        }
        System.out.println("a==>"+a);
    }



    /**
     * 利用cas 结果正确
     * @throws InterruptedException
     */
    @Test
    public void testCAS2() throws InterruptedException {
        for(int i=0;i<20;i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int j = 0; j <20000 ; j++) {
                        boolean success=false;
                        while (!success){
                            success=stat.compareAndSet(0,1);
                        }
                        incrA();
                        //state的值是volatile，这个操作之前的写操作对其他可见，符合heppns-before原则
                        stat.set(0);

                        incrB();
                    }
                }
            }).start();
        }
        while(Thread.activeCount()>1) {
            Thread.yield();
        }
        System.out.println("a==>"+a);
        System.out.println("b==>"+b);
    }



}
