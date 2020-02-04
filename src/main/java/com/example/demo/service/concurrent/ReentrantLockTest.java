package com.example.demo.service.concurrent;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author: laojiaqi
 * @Date: 2020-01-29 19:57
 * @Description:
 */
public class ReentrantLockTest {

    public static void main(String[] args) throws InterruptedException {
        ReentrantLock lock=new ReentrantLock();
        Condition condition = lock.newCondition();
        new Thread(new Runnable() {
            @Override
            public void run() {
                lock.lock();

                lock.unlock();
            }
        }).start();

        System.out.println("main start get lock");
        lock.lock();
        System.out.println("set s==1");
//        condition.signalAll();
        lock.unlock();
        System.out.println("unlock s==1");


        Thread.sleep(100000L);
    }
}
