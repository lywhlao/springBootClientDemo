package com.example.demo.service.concurrent.custom.semphore;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/**
 * 1表示有 0表示没有
 *
 * @Author: laojiaqi
 * @Date: 2020-02-13 17:05
 * @Description:
 */
public class CustomSemaphore extends AbstractQueuedSynchronizer {

    public CustomSemaphore(int state) {
        this.setState(state);
    }

    @Override
    protected boolean tryReleaseShared(int arg) {
        while (true) {
            int state = getState();
            int newState = state + arg;
            //newState 可能溢出
            boolean success = compareAndSetState(state, newState);
            if(success){
                return true;
            }
        }
    }

    @Override
    protected int tryAcquireShared(int arg) {
        while (true) {
            int state = getState();
            if (state <= 0) {
                return -1;
            }
            int remain = state - arg;
            if (remain < 0) {
                return -1;
            }
            //公平和非公平就差别在这里
            if(hasQueuedPredecessors()){
                return -1;
            }

            boolean success = compareAndSetState(state, remain);
            if (success) {
                return remain;
            }
        }
    }

    public void acquires(int num) throws InterruptedException {
        acquireSharedInterruptibly(num);
    }

    public void releases(int num){
        releaseShared(num);
    }


    public static void main(String[] args) throws InterruptedException {
        CustomSemaphore customSemaphore=new CustomSemaphore(3);
        System.out.println("main start==>acquire");
        customSemaphore.acquires(3);
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("start==>acquire");
                try {
                    customSemaphore.acquires(3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("end==>acquire");
            }
        }).start();
        Thread.sleep(1000);
        System.out.println("main start==>release");

        customSemaphore.releases(3);

    }


}
