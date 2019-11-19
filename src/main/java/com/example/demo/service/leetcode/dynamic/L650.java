package com.example.demo.service.leetcode.dynamic;

/**
 *
 * @Author: laojiaqi
 * @Date: 2019-11-18 22:00
 * @Description:
 */
public class L650 {


    public static void main(String[] args) {
//        minSteps(3);
//        minSteps(4);
//        minSteps(5);
        minSteps(6);
        minSteps(25);
    }

    public static int minSteps(int n) {
        int [] values=new int[n+1];
        if(n==0||n==1){
            return 0;
        }
        if(n==2){
            return 2;
        }
        values[0]=0;
        values[1]=0;
        values[2]=2;
        for(int i=2;i<=n;i++){
            setMinByNum(values,i);
        }
        System.out.println(values[n]);
        return values[n];
    }

    public static void setMinByNum(int [] values,int num){
        int sqrt=(int)Math.sqrt(num);
        int min=num;
        for (int i = 2; i <= sqrt; i++) {
            if(num %i ==0) {
                min = Math.min(min, values[num / i] + i);
            }
        }
        values[num]=min;
        System.out.println("num:"+num+",value="+min);
    }

}
