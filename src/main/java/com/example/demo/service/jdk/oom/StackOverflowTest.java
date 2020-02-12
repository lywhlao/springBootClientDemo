package com.example.demo.service.jdk.oom;

/**
 *
 * 请求栈的深度过大，通过-Xss可以调整线程栈的大小
 * @Author: laojiaqi
 * @Date: 2020-02-12 22:11
 * @Description:
 */
public class StackOverflowTest {


    int num = 0;

    public void loop() {
        num++;
        loop();
    }

    public static void main(String[] args) {

        StackOverflowTest stackOverflowTest=new StackOverflowTest();
        try {
            while(true){
                stackOverflowTest.loop();
            }
        } catch (Throwable e) {
            System.out.println(stackOverflowTest.num);
        }
    }
}
