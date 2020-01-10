package com.example.demo.service.leetcode.linkedList;

/**
 * @Author: laojiaqi
 * @Date: 2020-01-09 21:14
 * @Description:
 */
public class L1290 {

    public static void main(String[] args) {

        String a="111";
        int sum=0;
        for (int i = 0; i < a.length(); i++) {
            int i1 = a.charAt(i) - '0';
            sum|=i1;
            if(i!=a.length()-1){
                sum=sum<<1;
            }
        }
        System.out.println(sum);
    }
}
