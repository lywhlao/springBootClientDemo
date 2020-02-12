package com.example.demo;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: laojiaqi
 * @Date: 2020-02-12 21:23
 * @Description:
 */
public class jvm {

    int num=0;
    public void loop(){
        num++;
        loop();
    }

    public static void main(String[] args) {
        jvm jvm=new jvm();
        List<String> list=new ArrayList<>();
        int i=0;
        try {
            while (true){
                list.add(String.valueOf(i++).intern());
            }
        } catch (Throwable e) {
//            System.out.println(jvm.num);
//            System.out.println("length="+e.getStackTrace().length);
            e.printStackTrace();
        }
    }
    @Test
    public void stackOverFlowTest(){
        try {
            loop();
        } catch (Exception e) {
            System.out.println("length"+e.getStackTrace().length);
            e.printStackTrace();
        }
    }
}
