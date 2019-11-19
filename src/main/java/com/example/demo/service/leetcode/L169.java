package com.example.demo.service.leetcode;

import java.util.HashMap;
import java.util.Iterator;

/**
 * @Author: laojiaqi
 * @Date: 2019-11-19 14:56
 * @Description:
 */
public class L169 {

    public static void main(String[] args) {
        int [] nums={3,2,3};

        int i = majorityElement(nums);
        System.out.println(i);

    }

    public static int majorityElement(int[] nums) {
        HashMap<Integer,Integer> values=new HashMap<>();
        int length = nums.length;
        for (int i = 0; i < length; i++) {
            if(!values.containsKey(nums[i])){
                values.put(nums[i],1);
            }else{
                Integer integer = values.get(nums[i]);
                int i1 = integer;
                i1++;
                values.put(nums[i],i1);
            }
        }
        int max=0;
        int index=0;
        Iterator<Integer> iterator = values.keySet().iterator();
        while (iterator.hasNext()){
            Integer next = iterator.next();
            Integer integer = values.get(next);
            if(integer>max){
                max=integer;
                index=next;
            }
        }

        return index;
    }

}
