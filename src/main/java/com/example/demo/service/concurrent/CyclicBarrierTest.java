package com.example.demo.service.concurrent;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 *
 * 利用 retreenLock
 * @Author: laojiaqi
 * @Date: 2020-01-07 14:40
 * @Description:
 */
public class CyclicBarrierTest {

    public static void main(String[] args) throws BrokenBarrierException, InterruptedException {
        CyclicBarrier cyclicBarrier=new CyclicBarrier(2, new Runnable() {
            @Override
            public void run() {
                System.out.println("all in");
            }
        });
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println("child CyclicBarrier await");
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        System.out.println("main CyclicBarrier await");
        cyclicBarrier.await();
        System.out.println("main CyclicBarrier resume");

    }
}
