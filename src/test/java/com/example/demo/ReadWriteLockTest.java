package com.example.demo;

import org.junit.Test;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @Author: laojiaqi
 * @Date: 2019-11-22 15:09
 * @Description:
 */
public class ReadWriteLockTest {

    @Test
    public void test() throws InterruptedException {
        ReentrantReadWriteLock reentrantReadWriteLock=new ReentrantReadWriteLock();
        reentrantReadWriteLock.readLock().lock();
        new Thread(new Runnable() {
            @Override
            public void run() {

                reentrantReadWriteLock.readLock().lock();
            }
        }).start();

        Thread.sleep(10000L);
    }
}
