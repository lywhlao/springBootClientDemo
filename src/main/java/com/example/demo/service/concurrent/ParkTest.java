package com.example.demo.service.concurrent;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author: laojiaqi
 * @Date: 2019-11-20 09:48
 * @Description:
 */
public class ParkTest {

    public static int s=0;

    public static void main(String[] args) throws InterruptedException {
        ReentrantLock lock=new ReentrantLock();
        Condition condition = lock.newCondition();
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("park");
                LockSupport.park(this);
                System.out.println("unpark");

            }
        });
        System.out.println("start park");
        thread.start();;
        Thread.sleep(500);

        thread.interrupt();;
        System.out.println("start unpark");
        Thread.sleep(50000);

        CyclicBarrier cyclicBarrier=new CyclicBarrier(1);
//        LockSupport.unpark(thread);
    }
}
