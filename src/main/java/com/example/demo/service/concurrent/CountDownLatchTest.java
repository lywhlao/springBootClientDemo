package com.example.demo.service.concurrent;

import java.util.concurrent.CountDownLatch;

/**
 * @Author: laojiaqi
 * @Date: 2020-01-08 16:05
 * @Description:
 */
public class CountDownLatchTest {

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch=new CountDownLatch(1);

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("child countDown");
                countDownLatch.countDown();
            }
        }).start();

        System.out.println("main start await");
        countDownLatch.await();
        System.out.println("main resume");
    }
}
