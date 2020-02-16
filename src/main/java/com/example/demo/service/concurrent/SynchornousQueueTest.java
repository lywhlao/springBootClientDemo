package com.example.demo.service.concurrent;

import java.util.concurrent.SynchronousQueue;

/**
 * 不是很理解。
 *
 * @Author: laojiaqi
 * @Date: 2020-02-14 20:14
 * @Description:
 */
public class SynchornousQueueTest {

    public static void main(String[] args) throws InterruptedException {
        SynchronousQueue<String> synchronousQueue = new SynchronousQueue<>();

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("child start");
                String take = null;
                try {
                    take = synchronousQueue.take();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("child finish==>" + take);
            }
        }).start();
        System.out.println("main start");
        synchronousQueue.put("aa");
        System.out.println("main finish");
        Thread.sleep(1000L);


    }
}
