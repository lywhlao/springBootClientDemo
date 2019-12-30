package com.example.demo;

import org.junit.Test;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author: laojiaqi
 * @Date: 2019/9/11 11:48
 * @Description:
 */
public class JdkTest {


    @Test
    public void test(){
        ConcurrentHashMap<Object, Object> map = new ConcurrentHashMap<>();
        map.put("a","aa");
        map.get("a");
    }

}
