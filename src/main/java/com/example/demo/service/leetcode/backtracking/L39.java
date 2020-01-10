package com.example.demo.service.leetcode.backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: laojiaqi
 * @Date: 2020-01-10 18:07
 * @Description:
 */
public class L39 {

    public static void main(String[] args) {

        int [] ates={2,3,5};
        int target=8;
        List<List<Integer>> lists = combinationSum(ates, target);
        System.out.println(lists);
    }

    public static List<List<Integer>> combinationSum(int[] candidates, int target) {

        int length = candidates.length;
        int [] chose=new int[length];
        List<List<Integer>> reuslt=new ArrayList<>();
        find(candidates,0,target,chose,reuslt);

        return reuslt;
    }

    public static void find(int[] candidates,int index, int target,int [] chose,List<List<Integer>> result) {
        if(target==0){
            List<Integer> currentResult=new ArrayList<>();
            for (int i = 0; i < chose.length; i++) {
                 if(chose[i]>0){
                     for (int k = 0; k <chose[i]; k++) {
                          currentResult.add(candidates[i]);
                     }
                 }
            }
            result.add(currentResult);
            return;
        }
        if(target<0){
            return;
        }
        if(index>=candidates.length){
            return;
        }
        int tempIndex=index;

        //选择当前index
        int candidate = candidates[index];
        int remain = target - candidate;
        boolean plusFlag=false;
        if(remain>=0){
            chose[index]++;
            find(candidates,tempIndex,remain,chose,result);
            plusFlag=true;
//            System.out.println("remain:"+chose[index]);
        }

        if(plusFlag){
            //未选择当前index
            chose[index]--;
//            System.out.println("not remain:"+chose[index]);

        }
        find(candidates,++index,target,chose,result);
    }

}
