package com.example.demo.service.concurrent;

import java.util.concurrent.CountDownLatch;

/**
 * @Author: laojiaqi
 * @Date: 2020-01-08 16:05
 * @Description:
 */
public class CountDownLatchTest {

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch=new CountDownLatch(2);

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("child countDown");
                countDownLatch.countDown();
                try {
                    System.out.println("child await");
                    countDownLatch.await();
                    System.out.println("child resume");

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        System.out.println("main countDown");
        countDownLatch.countDown();
        System.out.println("main  await");
        countDownLatch.await();
        System.out.println("main resume");
    }
}
