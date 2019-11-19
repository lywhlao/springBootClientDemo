package com.example.demo.service.leetcode.concurrent;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author: laojiaqi
 * @Date: 2019-11-17 21:48
 * @Description:
 */
public class L1116 {

    public static void main(String[] args) throws InterruptedException {
        ReentrantLock reentrantLock=new ReentrantLock();
        Condition condition = reentrantLock.newCondition();
        condition.await();

        reentrantLock.lock();
    }
}
