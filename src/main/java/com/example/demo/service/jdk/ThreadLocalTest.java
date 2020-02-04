package com.example.demo.service.jdk;

/**
 * @Author: laojiaqi
 * @Date: 2020-02-04 13:03
 * @Description:
 */
public class ThreadLocalTest {

    public static void main(String[] args) {
        ThreadLocal<String> local=new ThreadLocal<>();
        local.set("test");

        local.get();
    }
}
