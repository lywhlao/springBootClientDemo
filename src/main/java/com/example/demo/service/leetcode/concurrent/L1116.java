package com.example.demo.service.leetcode.concurrent;

import java.util.function.IntConsumer;

/**
 * @Author: laojiaqi
 * @Date: 2019-11-17 21:48
 * @Description:
 */
public class L1116 {

    public static void main(String[] args) throws InterruptedException {
        ZeroEvenOdd zeroEvenOdd = new ZeroEvenOdd(10);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    zeroEvenOdd.zero((x) -> {
                        System.out.println("thread-1 ==》A");
                    });
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    zeroEvenOdd.odd((x) -> {
                        System.out.println("thread-2 ==》B");
                    });
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    zeroEvenOdd.even((x) -> {
                        System.out.println("thread-1 ==》C");
                    });
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        synchronized (zeroEvenOdd) {
            zeroEvenOdd.wait();
        }
    }


    static class ZeroEvenOdd {
        /**
         * size
         */
        private int n;
        /**
         *  next print num
         */
        private int candidatePrintNum = 0;
        /**
         * 0 --> print 0;
         * 1 --> print odd;
         * 2 --> print even
         */
        private int status = 0;

        public ZeroEvenOdd(int n) {
            this.n = n;
            this.candidatePrintNum=0;
            this.status=0;
        }

        // printNumber.accept(x) outputs "x", where x is an integer.
        public void zero(IntConsumer printNumber) throws InterruptedException {
            synchronized (this) {
                while (candidatePrintNum <= n) {
                    while (status != 0 && candidatePrintNum <= n) {
                        this.wait();
                    }
                    candidatePrintNum++;
                    if (candidatePrintNum > n) {
                        notifyAll();
                        break;
                    }
                    printNumber.accept(0);
                    if (candidatePrintNum % 2 == 0) {
                        status = 2;
                    } else {
                        status = 1;
                    }
                    notifyAll();
                }
            }
//            System.out.println("zero thread end");
        }

        public void even(IntConsumer printNumber) throws InterruptedException {
            synchronized (this) {
                while (candidatePrintNum <= n) {
                    while (status != 1 && candidatePrintNum <= n) {
                        this.wait();
                    }
                    if (candidatePrintNum > n) {
                        notifyAll();
                        break;
                    }
                    printNumber.accept(candidatePrintNum);
                    status = 0;
                    notifyAll();
                }
            }
//            System.out.println("even thread end");
        }

        public void odd(IntConsumer printNumber) throws InterruptedException {
            synchronized (this) {
                while (candidatePrintNum <= n) {
                    while (status != 2 && candidatePrintNum <= n) {
                        this.wait();
                    }
                    if (candidatePrintNum > n) {
                        notifyAll();
                        break;
                    }
                    printNumber.accept(candidatePrintNum);
                    status = 0;
                    notifyAll();
                }
            }
//            System.out.println("odd thread end");
        }
    }
}
