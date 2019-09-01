package com.example.demo;

import org.junit.Test;

import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @Author: laojiaqi
 * @Date: 2019/7/25 19:53
 * @Description:
 */
public class CopyOnWriteArrayListTest {


    @Test
    public void test(){
        CopyOnWriteArrayList<String> list=new CopyOnWriteArrayList<>();
        list.add("a");
        list.get(0);

    }
}
