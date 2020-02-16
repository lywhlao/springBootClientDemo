package com.example.demo.service.concurrent.queue;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * @Author: laojiaqi
 * @Date: 2020-02-14 22:03
 * @Description:
 */
public class LinkedBlackingQueueTest {

    public static void main(String[] args) throws InterruptedException {
        LinkedBlockingQueue<String> queue=new LinkedBlockingQueue<>(1);

        queue.add("hello");

        queue.take();
    }
}
