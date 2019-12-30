package com.example.demo.service.leetcode.backtracking;

import org.bouncycastle.util.Strings;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * Examples:
 * Input: S = "a1b2"
 * Output: ["a1b2", "a1B2", "A1b2", "A1B2"]
 *
 * @Author: laojiaqi
 * @Date: 2019-12-21 15:50
 * @Description:
 */
public class L784 {

    public static void main(String[] args) {
        letterCasePermutation("a1b2");
//        letterCasePermutation("A1b2");
//        letterCasePermutation("A1B2");
//        letterCasePermutation("12345");
    }


    public static List<String> letterCasePermutation(String S) {
        int length = S.length();
        List<String> result=new ArrayList<>();
        changeCase(0, length - 1, S.toCharArray(),result);
        return result;
    }

    public static boolean isWord(String s){
        Pattern pattern=Pattern.compile("[^A-Za-z]+");
        Matcher matcher = pattern.matcher(s);
        if(matcher.find()){
            return true;
        }
        return false;
    }

    public static void changeCase(int startIndex,int maxIndex,char[] values,List<String> result) {
        if(startIndex>maxIndex){
            result.add(new String(values));
            return;
        }
        char changeValue = values[startIndex];

        if(changeValue>='0' & changeValue<='9'){
            changeCase(++startIndex,maxIndex,values,result);
            return;
        }

        int temp=startIndex;
        values[startIndex]=Character.toLowerCase(changeValue);
        changeCase(++temp,maxIndex,values,result);


        temp=startIndex;
        values[startIndex]=Character.toUpperCase(changeValue);

        changeCase(++temp,maxIndex,values,result);
    }
}
