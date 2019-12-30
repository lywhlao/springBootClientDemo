package com.example.demo.service.leetcode.backtracking;

/**
 * @Author: laojiaqi
 * @Date: 2019-12-16 08:26
 * @Description:
 */
public class SubSetSum {

    /**
     *
     * Given a set X of positive integers and target integer T,
     * is there a subset of elements in X that add up to T?
     * Notice that there can be more than one such subset.
     * For example, if X = {8,6,7,5,3,10,9} and T = 15, the answer is True,
     * because the subsets {8,7} and {7,5,3} and {6,9} and {5,10} all sum to 15.
     * @param args
     */
    public static void main(String[] args) {

        int [] X={8,6,7,5,3,10,9};
        int length = X.length;
        int sum=15;
        for (int i = length-1; i>=0; i--) {
            boolean construcat = construcat(X, i, sum);
            if(construcat){
                System.out.println(true);
                break;
            }
        }


    }

    public static boolean construcat(int []X,int i,int sum){
        if(sum==0){
            return true;
        }
        if(sum<0||i==0){
            return false;
        }
        //包含
        boolean contains = construcat(X, i - 1, sum - X[i]);

        //不包含
        boolean notContains = construcat(X, i - 1, sum);

        return contains||notContains;
    }


}
