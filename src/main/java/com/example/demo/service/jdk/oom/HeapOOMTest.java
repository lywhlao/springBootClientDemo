package com.example.demo.service.jdk.oom;

import java.util.ArrayList;
import java.util.List;

/**
 * 常量池 -Xmx1m
 * @Author: laojiaqi
 * @Date: 2020-02-12 22:14
 * @Description:
 */
public class HeapOOMTest {



    public static void main(String[] args) {
        List<String> list=new ArrayList<>();
        int i=0;
        try {
            while (true){
                list.add(String.valueOf(i++).intern());
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }
}
