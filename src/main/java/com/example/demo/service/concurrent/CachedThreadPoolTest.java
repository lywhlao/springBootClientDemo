package com.example.demo.service.concurrent;

import java.util.concurrent.*;

/**
 * @Author: laojiaqi
 * @Date: 2020-02-14 20:28
 * @Description:
 */
public class CachedThreadPoolTest {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();

        executorService.submit(new Callable<Object>() {
            @Override
            public Object call() throws Exception {
                return "hello";
            }
        });

        BlockingQueue<String> blockingQueue=new LinkedBlockingQueue<>();

    }
}
