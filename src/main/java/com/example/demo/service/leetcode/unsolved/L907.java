package com.example.demo.service.leetcode.unsolved;

/**
 * 原因：超时
 * @Author: laojiaqi
 * @Date: 2019-11-28 21:20
 * @Description:
 */
public class L907 {

    public static void main(String[] args) {
        int[] array = {3, 1, 2, 4};
        int i = sumSubarrayMins(array);
        System.out.println(i);
    }

    public static int sumSubarrayMins(int[] A) {
        if (A == null) {
            return 0;
        }
        int length = A.length;
        long sum = 0;
        for (int stackSize = 1; stackSize <= length; stackSize++) {
            int lastStackMinimumIndex = -1;
            for (int i = 0; i < A.length; i++) {
                int maxStackIndex = i + stackSize - 1;
                if (maxStackIndex >= A.length) {
                    continue;
                }
                int minStackIndex = i;
                if(lastStackMinimumIndex!=-1){
                    if(minStackIndex<=lastStackMinimumIndex && lastStackMinimumIndex <=maxStackIndex){
                        if(A[lastStackMinimumIndex]>A[maxStackIndex]){
                            lastStackMinimumIndex=maxStackIndex;
                            sum+=A[maxStackIndex];
                            continue;
                        }
                    }
                }
                int minimumIndex = findMinimumIndex(minStackIndex, maxStackIndex, A);
                lastStackMinimumIndex=minimumIndex;
                sum+=A[minimumIndex];
            }
        }
        return (int) (sum % (Math.pow(10, 9) + 7));
    }

    public static int findMinimumIndex(int start, int end, int[] A) {
        int min = start;
        for (int i = start + 1; i <= end; i++) {
            if (A[min] > A[i]) {
                min = i;
            }
        }
        return min;
    }

}
