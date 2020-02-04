package com.example.demo.service.concurrent;

import java.util.concurrent.*;

/**
 * @Author: laojiaqi
 * @Date: 2020-02-03 16:45
 * @Description:
 */
public class FurtureTaskTest {

    public static void main(String[] args) {
        FutureTask futureTask=new FutureTask(new Callable() {
            @Override
            public Object call() throws Exception {
                System.out.println("this is test");
                return null;
            }
        });
        futureTask.run();


        ExecutorService executorService = Executors.newFixedThreadPool(2);
        Future<?> submit = executorService.submit(new Runnable() {
            @Override
            public void run() {
                System.out.println("runnable");
            }
        });

        executorService.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println("hello!");
            }
        });

        executorService.submit(new Callable<Object>() {
            @Override
            public Object call() throws Exception {
                System.out.println("callable");
                return null;
            }
        });


    }
}
