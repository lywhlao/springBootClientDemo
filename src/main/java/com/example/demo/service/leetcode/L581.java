package com.example.demo.service.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.locks.LockSupport;

/**
 * @Author: laojiaqi
 * @Date: 2019-11-17 18:48
 * @Description:
 */
public class L581 {

    public static void main(String[] args) {
        int [] nums={1,2,3,4};
        int unsortedSubarray = findUnsortedSubarray(nums);
        System.out.println(unsortedSubarray);

    }

    public static int findUnsortedSubarray(int[] nums) {
        List<Integer> list = new ArrayList<>(nums.length);
        for (int i = 0; i < nums.length; i++) {
            list.add(nums[i]);
        }
        List<Integer> copiedList = new ArrayList<>(list);
        Collections.sort(copiedList);
        int startIndex=-1;
        int lastIndex=-1;
        for (int i = 0; i < copiedList.size(); i++) {
            if (!copiedList.get(i).equals(list.get(i)) ) {
                if(startIndex==-1){
                    startIndex=i;
                }
                lastIndex=i;
            }
        }
        if(startIndex==-1){
            return 0;
        }
        return lastIndex-startIndex+1;
    }
}
