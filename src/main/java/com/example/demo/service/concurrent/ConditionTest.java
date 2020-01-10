package com.example.demo.service.concurrent;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author: laojiaqi
 * @Date: 2019-11-20 09:48
 * @Description:
 */
public class ConditionTest {

    public static int s=0;

    public static void main(String[] args) throws InterruptedException {
        ReentrantLock lock=new ReentrantLock();
        Condition condition = lock.newCondition();
        new Thread(new Runnable() {
            @Override
            public void run() {
                lock.lock();
                try {
                    if(s!=1) {
                        System.out.println("wait s==1");
                        condition.await();
                        System.out.println("get s==1");
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                lock.unlock();
            }
        }).start();

        System.out.println("main start get lock");
        lock.lock();
        s=1;
        System.out.println("set s==1");
//        condition.signalAll();
        lock.unlock();
        System.out.println("unlock s==1");


        Thread.sleep(100000L);



    }



}
