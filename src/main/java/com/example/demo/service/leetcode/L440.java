package com.example.demo.service.leetcode;

import com.google.common.collect.Sets;
import org.redisson.misc.Hash;

import java.util.*;

/**
 * 超时
 * @Author: laojiaqi
 * @Date: 2019-11-15 20:36
 * @Description:
 */
public class L440 {

    public static void main(String[] args) {
//        int n=4289384;
//        int k=1922239;
        int n=13;
        int k=2;
        System.out.println(findKthNumber(n,k));

    }

    public static int findKthNumber(int n, int k) {
        String  result="";
        Map<Integer, Set<String>> hashMap=new HashMap<>();
        for (int i = 1; i <= n; i++) {
            String value=i+"";
            String c = value.substring(0,1);
            int cInt = Integer.parseInt(c);
            if(!hashMap.containsKey(cInt)){
                hashMap.put(Integer.parseInt(c),new TreeSet<>());
            }
            Set<String> treeSet = hashMap.get(cInt);
            treeSet.add(value);
        }

        int totalNum=0;
        for (int i = 1; i <= 9; i++) {
            Set<String> item = hashMap.get(i);
            if(item==null){
                continue;
            }
            totalNum=item.size();
            if(totalNum>=k){
                result = find(item, k);
                break;
            }else{
                k=k-totalNum;
            }
        }
        int i = Integer.parseInt(result);
        return i;
    }


    public static String find(Set<String> sets,int k){
        Iterator<String> iterator = sets.iterator();
        int count=1;
        String result="";
        while (iterator.hasNext()){
            String next = iterator.next();
            if(count==k){
                result=next;
                break;
            }
            count++;
        }
        return result;
    }
}
