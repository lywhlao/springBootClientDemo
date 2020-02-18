package com.example.demo.service.jdk.proxy;

/**
 * @Author: laojiaqi
 * @Date: 2020-02-17 21:55
 * @Description:
 */
public class MainTest implements IMainTest {

    @Override
    public void hell0(String arg1) {
        System.out.println(arg1);
    }
}
