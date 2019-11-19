package com.example.demo.service.leetcode;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @Author: laojiaqi
 * @Date: 2019-11-19 21:28
 * @Description:
 */
public class L455 {

    public static void main(String[] args) {
        int [] g={1,2};
        int [] s={1,6,5};

        System.out.println(findContentChildren(g,s));
    }

    public static int findContentChildren(int[] g, int[] s) {
        Arrays.sort(s);
        Arrays.sort(g);
        int count=0;
        Set<Integer> assginSet=new HashSet<>();
        for (int i = s.length - 1; i >= 0; i--) {
            for (int j = g.length - 1; j >= 0; j--) {
                 if(!assginSet.contains(j)) {
                     assginSet.add(j);
                     if (s[i] >= g[j]) {
                         count++;
                         break;
                     }
                 }
            }
        }
        return count;
    }
}
