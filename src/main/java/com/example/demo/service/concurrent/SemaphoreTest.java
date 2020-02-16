package com.example.demo.service.concurrent;

import java.util.concurrent.Semaphore;

/**
 * @Author: laojiaqi
 * @Date: 2020-02-13 18:18
 * @Description:
 */
public class SemaphoreTest {

    public static void main(String[] args) throws InterruptedException {

        Semaphore semaphore=new Semaphore(1);

        semaphore.acquire(1);

        new Thread(new Runnable() {
            @Override
            public void run() {
                semaphore.release(1);
                System.out.println("child thread release");
            }
        }).start();
        Thread.sleep(2000L);
        semaphore.release();
    }
}
