package com.example.demo.service.classloader;

/**
 * @Author: laojiaqi
 * @Date: 2020-01-06 20:10
 * @Description:
 */
public class CustomClassLoader extends ClassLoader{

    public static void main(String[] args) {
        ClassLoader aa=new ClassLoader() {
            @Override
            public Class<?> loadClass(String name) throws ClassNotFoundException {
                return super.loadClass(name);
            }
        };
    }
}
