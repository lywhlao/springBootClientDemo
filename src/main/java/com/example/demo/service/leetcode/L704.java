package com.example.demo.service.leetcode;

import javafx.scene.control.TableRow;

import java.util.Arrays;

/**
 * @Author: laojiaqi
 * @Date: 2019-11-21 08:37
 * @Description:
 */
public class L704 {
    public static void main(String[] args) {
        int [] nums={-1,0,3,5,9,12};
        int search = search(nums, 12);
        System.out.println(search);
    }

    public static int search(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }

        int maxIndex = nums.length - 1;
        if (nums[0] > target || nums[maxIndex] < target) {
            return -1;
        }

        int startIndex = 0;
        int endIndex = maxIndex;

        while (endIndex - startIndex > 1) {
            int temp=(endIndex+startIndex)/2;
            if(nums[temp]>target){
                endIndex=temp;
            }else{
                startIndex=temp;
            }
        }

        for (int i = startIndex; i <= endIndex; i++) {
            if (nums[i] == target) {
                return i;
            }
        }
        return -1;
    }

}
