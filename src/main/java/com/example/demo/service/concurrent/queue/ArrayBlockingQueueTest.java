package com.example.demo.service.concurrent.queue;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * @Author: laojiaqi
 * @Date: 2020-02-14 22:54
 * @Description:
 */
public class ArrayBlockingQueueTest {


    public static void main(String[] args) {

        ArrayBlockingQueue<String> blockingQueue=new ArrayBlockingQueue<String>(1);

        blockingQueue.add("hello");
        blockingQueue.add("hello");


    }
}
