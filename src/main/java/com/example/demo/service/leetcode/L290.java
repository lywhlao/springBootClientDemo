package com.example.demo.service.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: laojiaqi
 * @Date: 2019-12-04 22:45
 * @Description:
 */
public class L290 {
    public static void main(String[] args) {
        boolean b = wordPattern("abcd", "dog cat adf qqq");
        System.out.println(b);

        boolean c = wordPattern("aba", "dog cat fish");
        System.out.println(c);

        System.out.println(wordPattern("abba", "dog d1g d1g dog"));;
    }

    public static boolean wordPattern(String pattern, String str) {
        if(pattern==null && str==null){
            return true;
        }
        if(pattern!=null && str!=null){
            String[] split = str.split("\\s+");
            int length = pattern.length();
            if(split.length!=length){
                return false;
            }
            Map<Character,String> map=new HashMap<>();
            Map<String,Character> reserveMap=new HashMap<>();
            for (int i = 0; i < length ; i++) {
                char c = pattern.charAt(i);
                if(map.containsKey(c)){
                    String s = map.get(c);
                    if(!s.equals(split[i])){
                        return false;
                    }
                    continue;
                }
                map.put(c,split[i]);
                if(reserveMap.containsKey(split[i])){
                    Character character = reserveMap.get(split[i]);
                    if(!character.equals(c)){
                        return false;
                    }
                }
                reserveMap.put(split[i],c);
            }
            return true;
        }
        return false;
    }
}
