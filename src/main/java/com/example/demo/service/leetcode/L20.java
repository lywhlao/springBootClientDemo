package com.example.demo.service.leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * @Author: laojiaqi
 * @Date: 2019-11-16 08:40
 * @Description:
 */
public class L20 {

    public static void main(String[] args) {
        System.out.println(isValid("()"));
        System.out.println(isValid("()[]{}")); ;
        System.out.println(isValid("(]"));
        System.out.println(isValid("([)]"));
        System.out.println(isValid("{[]}"));
        System.out.println(isValid("]"));
    }

    public static boolean isValid(String s) {
        int length = s.length();
        List<Character> list=new ArrayList<>();
        for (int i = 0; i < length; i++) {
            if(isLeft(s.charAt(i))){
                list.add(s.charAt(i));
            }else{
                boolean andPop = findAndPop(list, s.charAt(i));
                if(!andPop){
                    return false;
                }
            }

        }
        if(list.size()==0){
            return true;
        }
        return false;
    }


    public static boolean isLeft(char a){
        if(a=='('){
            return true;
        }
        if(a=='{'){
            return true;
        }
        if(a=='['){
            return true;
        }
        return false;
    }


    public static boolean findAndPop(List<Character> list, char b){
        if(list.size()==0 && !isLeft(b)){
            return false;
        }
        int size = list.size();
        if(b==')'){
            if(list.get(size-1)!='('){
                return false;
            }
            list.remove(size-1);
        }
        if(b=='}'){
            if(list.get(size-1)!='{'){
                return false;
            }
            list.remove(size-1);
        }
        if(b==']'){
            if(list.get(size-1)!='['){
                return false;
            }
            list.remove(size-1);
        }
        return true;
    }

}
