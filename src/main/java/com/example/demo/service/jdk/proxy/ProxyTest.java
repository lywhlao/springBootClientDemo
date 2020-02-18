package com.example.demo.service.jdk.proxy;

import java.lang.reflect.Proxy;

/**
 * @Author: laojiaqi
 * @Date: 2020-02-17 21:52
 * @Description:
 */
public class ProxyTest {


    public static void main(String[] args) throws Throwable {

        MainTest mainTest = new MainTest();

        CustomInvocationHandler customInvocationHandler = new CustomInvocationHandler(mainTest);


        IMainTest proxyInstance = (IMainTest) Proxy.newProxyInstance(ClassLoader.getSystemClassLoader(), new Class[]{IMainTest.class}, customInvocationHandler);

        proxyInstance.hell0("hello");
    }
}
