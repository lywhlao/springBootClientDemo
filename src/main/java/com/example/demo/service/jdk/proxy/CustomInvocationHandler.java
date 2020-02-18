package com.example.demo.service.jdk.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @Author: laojiaqi
 * @Date: 2020-02-17 21:52
 * @Description:
 */
public class CustomInvocationHandler implements InvocationHandler {


    public MainTest mainTest;

    public CustomInvocationHandler(MainTest mainTest) {
        this.mainTest = mainTest;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        System.out.println("====> before");
        Object invoke = method.invoke(mainTest, args);
        System.out.println("====> after");
        return invoke;
    }
}
