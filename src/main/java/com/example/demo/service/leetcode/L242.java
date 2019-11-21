package com.example.demo.service.leetcode;

import java.util.Arrays;

/**
 * @Author: laojiaqi
 * @Date: 2019-11-20 22:48
 * @Description:
 */
public class L242 {

    public static void main(String[] args) {

        isAnagram("a","ab");
    }

    public static boolean isAnagram(String s, String t) {
        if(s.length()!=t.length()){
            return false;
        }
        char[] sc = s.toCharArray();
        char[] tc = t.toCharArray();
        Arrays.sort(sc);
        Arrays.sort(tc);
        boolean result=true;
        for (int i = 0; i < sc.length; i++) {
            if(sc[i]!=tc[i]){
                result=false;
            }
        }
        return result;
    }

}
