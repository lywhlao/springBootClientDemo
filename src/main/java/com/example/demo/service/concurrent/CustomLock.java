package com.example.demo.service.concurrent;

import org.openqa.selenium.support.ui.Wait;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/**
 * @Author: laojiaqi
 * @Date: 2020-02-05 23:15
 * @Description:
 */
public class CustomLock {
    public static class  CustomSync extends AbstractQueuedSynchronizer {

        public CustomSync(int arg) {
            super.setState(arg);
        }

        @Override
        protected boolean tryAcquire(int arg) {
            int state = getState();
            //可重入
            if(state==0){
                if(Thread.currentThread()==getExclusiveOwnerThread()){
                    return true;
                }
            }

            boolean success = compareAndSetState(arg, 0);
            if(success){
                setExclusiveOwnerThread(Thread.currentThread());
                return true;
            }
            return false;
        }

        @Override
        protected boolean tryRelease(int arg) {
            if(getState()==1){
                throw new RuntimeException("not locked");
            }
            if(getExclusiveOwnerThread()!=Thread.currentThread()){
                throw new RuntimeException("error");
            }
            boolean success = compareAndSetState(arg, 1);
            if(success){
                setExclusiveOwnerThread(null);
                return true;
            }
            return false;
        }

        public void lock(){
            acquire(1);
        }

        public void unlock(){
            release(0);
        }

    }

    public static void main(String[] args) {
        CustomSync customSync=new CustomSync(1);
        List<Thread> threadList=new ArrayList<>();
        final int[] a = {1};
        for (int i = 0; i < 2; i++) {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int j = 0; j < 10; j++) {
                        System.out.println(Thread.currentThread().getName() + "==>child start getting");
                        customSync.lock();
                        System.out.println(Thread.currentThread().getName() + "==>child get lock");
                        customSync.unlock();
                        System.out.println(Thread.currentThread().getName() + "==>child release lock");
                    }
                }
            });
            threadList.add(thread);
        }

        for(Thread temp:threadList){
            temp.start();;
        }

        System.out.println("main start getting");
        customSync.lock();
        System.out.println("main get locked");
        customSync.unlock();
        System.out.println("main release lock");

        System.out.println("sum=>"+a[0]);
        Thread.yield();
    }
}
