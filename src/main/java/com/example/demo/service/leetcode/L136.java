package com.example.demo.service.leetcode;

import java.util.BitSet;
import java.util.HashSet;
import java.util.Set;

/**
 * @Author: laojiaqi
 * @Date: 2019-11-15 22:15
 * @Description:
 */
public class L136 {

//    public static void main(String[] args) {
//        BitSet bitSetOne=new BitSet();
//        BitSet bitSetTwo=new BitSet();
//        int result=0;
//        int [] nums={2,2,1};
//        for (int i = 0; i < nums.length; i++) {
//             if(!bitSetOne.get(nums[i])){
//                 bitSetOne.set(nums[i]);
//             }else {
//                 bitSetTwo.set(nums[i]);
//             }
//        }
//        bitSetOne.xor(bitSetTwo);
//        for (int i = 0; i < nums.length; i++) {
//            if(bitSetOne.get(nums[i])){
//                result=nums[i];
//            }
//        }
//        System.out.println(result);
//    }

    public static void main(String[] args) {
        Set<Integer> setOne=new HashSet<>();
        Set<Integer> setTwo=new HashSet<>();
        int result=0;
        int [] nums={2,2,1};
        for (int i = 0; i < nums.length; i++) {
             if(!setOne.contains(nums[i])){
                 setOne.add(nums[i]);
             }else {
                 setTwo.add(nums[i]);
             }
        }
        setOne.removeAll(setTwo);
        Integer next = setOne.iterator().next();
        System.out.println(next);
    }
}
