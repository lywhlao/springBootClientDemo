package com.example.demo.service.leetcode;

import org.springframework.util.CollectionUtils;

import java.util.*;

/**
 * @Author: laojiaqi
 * @Date: 2019-11-19 20:14
 * @Description:
 */
public class L15 {

    public static void main(String[] args) {
//        int[] nums={-2,0,1,1,2};
        int[] nums2={0,0,0,0};
        int target=1;
//        System.out.println(threeSumClosest(nums));
        System.out.println(threeSumClosest(nums2));
    }

    public static  List<List<Integer>> threeSumClosest(int[] nums) {

        List<List<Integer>> result=new ArrayList<>();
        Set<String> flagSet=new HashSet<>();
        List<Integer> numsList=new ArrayList<>();

        for (int i = 0; i < nums.length; i++) {
            numsList.add(nums[i]);
        }
        Collections.sort(numsList);
        for (int i = 0; i < numsList.size(); i++) {
            int currentValue=numsList.get(i);
            int start=i+1;
            int end=numsList.size()-1;
            while (start<end){
                Integer startValue = numsList.get(start);
                Integer endValue = numsList.get(end);
                if(startValue+endValue+currentValue==0){
                    String s = currentValue + "" + startValue + "" +endValue;
                    if(!flagSet.contains(s)){
                        flagSet.add(s);
                        result.add(Arrays.asList(currentValue,startValue,endValue));
                    }
                    start++;
                    end--;
                }
                if(startValue+endValue+currentValue>0){
                    end--;
                }
                if(startValue+endValue+currentValue<0){
                    start++;
                }

            }
        }
        return result;
    }


//     for (int i = 0; i < nums.length; i++) {
//        for (int j = i+1; j < nums.length; j++) {
//            for (int k = j+1; k < nums.length; k++) {
//                int i1 = nums[i] + nums[j] + nums[k];
//                if(i1==0) {
//                    List<Integer> integers = Arrays.asList(nums[i], nums[j], nums[k]);
//                    Collections.sort(integers);
//                    String s = integers.get(0) + "" + integers.get(1) + "" + integers.get(2);
//                    if(!flagSet.contains(s)){
//                        flagSet.add(s);
//                        result.add(integers);
//                    }
//                    break;
//                }
//            }
//        }
//    }
//        return result;

//     if(i1==0) {
//        List<Integer> integers = Arrays.asList(nums[i], nums[j], nums[k]);
//        Collections.sort(integers);
//        String s = integers.get(0) + "" + integers.get(1) + "" + integers.get(2);
//        if(!flagSet.contains(s)){
//            flagSet.add(s);
//            result.add(integers);
//        }
//    }
}
