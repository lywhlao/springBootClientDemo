package com.example.demo.service.leetcode.backtracking;

import java.util.*;

/**
 * @Author: laojiaqi
 * @Date: 2019-12-30 19:55
 * @Description:
 */
public class L401 {

    public static void main(String[] args) {
        readBinaryWatch(10);
    }

    public static List<String> readBinaryWatch(int num) {
        List<String> result = new LinkedList<>();
        for (int h = 0; h <= num; h++) {
            int [] hourArray=new int[4];
            int [] minuteArray=new int[6];
            Set<String> hourResultList=new HashSet<String>();
            Set<String> minuteResultList=new HashSet<String>();
            handleLight(hourArray,h,0,hourResultList);
            handleLight(minuteArray,num-h,0,minuteResultList);

            Iterator<String> hourIterator = hourResultList.iterator();
            while (hourIterator.hasNext()){
                String hour = hourIterator.next();
                Iterator<String> iterator = minuteResultList.iterator();
                while (iterator.hasNext()){
                    StringBuilder sb=new StringBuilder();
                    int hourInt = Integer.parseInt(hour);
                    String minuteStr = iterator.next();
                    int minuteInt = Integer.parseInt(minuteStr);
                    if(hourInt>11||minuteInt>59){
                        continue;
                    }
                    sb.append(hourInt+":");

                    if(minuteInt<10){
                        sb.append("0");
                        sb.append(minuteInt);
                    }else {
                        sb.append(minuteInt);
                    }
                    result.add(sb.toString());
                }
            }
        }
        return result;
    }



    public static void handleLight(int array[], int count, int index, Set<String> result){
        if(count<=0){
            int hours=0;
            for (int i = 0; i < array.length; i++) {
                if(array[i]==1){
                    hours+=1*Math.pow(2,i);
                }
            }
            result.add(hours+"");
            return;
        }
        if(index>=array.length){
            return;
        }
        int tempIndex=index;
        int tempCount=count;
        //index position is light
        array[index]=1;
        handleLight(array,--tempCount,++tempIndex,result);

        //index position is dark
        array[index]=0;
        handleLight(array,count,++index,result);
    }


}
