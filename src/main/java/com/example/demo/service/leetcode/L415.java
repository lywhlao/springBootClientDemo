package com.example.demo.service.leetcode;

/**
 * @Author: laojiaqi
 * @Date: 2019-11-19 11:41
 * @Description:
 */
public class L415 {

    public static void main(String[] args) {
        String num1="1";
        String num2="9";
        System.out.println(addStrings(num1,num2));
        System.out.println(addStrings(num2,num1));
    }


    public static String addStrings(String num1, String num2) {
        int length = num1.length();
        char []  values= new StringBuilder(num1).reverse().toString().toCharArray();
        int length2 = num2.length();
        char []  values2= new StringBuilder(num2).reverse().toString().toCharArray();


        int max = Math.max(length, length2);
        char [] result=new char[max+1];
        if(length2>length){
            char [] temp=values;
            values=values2;
            values2=temp;
        }
        length=values.length;
        length2=values2.length;
        int add=0;
        for (int i = 0; i<length; i++) {
            int i1 = values[i] - '0';
            int i2=0;
            if(i<length2){
                i2 = values2[i]-'0';
            }
            int singleSum = i1 + i2 + add;
            add=0;
            if(singleSum>9){
                add=1;
                result[i]=(char)(singleSum-10+'0');
            }else{
                result[i]=(char)(singleSum+'0');
            }
        }
        if(add==1){
            result[max]=1+'0';
            return new StringBuilder(new String(result)).reverse().toString();
        }else{
            return new StringBuilder(new String(result,0,max)).reverse().toString();
        }
    }

}
