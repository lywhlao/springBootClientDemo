package com.example.demo.service.classloader;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author: laojiaqi
 * @Date: 2020-01-06 20:10
 * @Description:
 */
public class CustomClassLoader extends ClassLoader{

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        return super.findClass(name);
    }

    public static void main(String[] args) {
        String.valueOf(1).intern();
        ReentrantLock reentrantLock=new ReentrantLock();
        reentrantLock.lock();;
        ClassLoader aa=new ClassLoader() {
            @Override
            public Class<?> loadClass(String name) throws ClassNotFoundException {

                return super.loadClass(name);
            }
        };
    }
}
