package com.example.demo.service.leetcode.linkedList;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @Author: laojiaqi
 * @Date: 2020-01-09 20:23
 * @Description:
 */
public class L923 {

    public static void main(String[] args) {
        int[] A = {1, 1, 2, 2, 3, 3, 4, 4, 5, 5};
        int i = threeSumMulti(A, 8);
        System.out.println(i);
    }

    public static int threeSumMulti(int[] A, int target) {
        Map<Integer, Integer> maps = new HashMap<>();
        for (int i = 0; i < A.length; i++) {
            Integer integer = maps.get(A[i]);
            if (integer != null) {
                maps.put(A[i], ++integer);
            } else {
                maps.put(A[i], 1);
            }
        }
        Integer[] values = maps.keySet().toArray(new Integer[0]);
        for (int i = 0; i < values.length; i++) {
            Integer sum = maps.get(values[i]);
//            if(sum>0)
        }
        return 1;//todo
    }

}
