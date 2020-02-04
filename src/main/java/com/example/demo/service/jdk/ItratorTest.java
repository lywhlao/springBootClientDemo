package com.example.demo.service.jdk;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

/**
 * @Author: laojiaqi
 * @Date: 2020-02-04 12:23
 * @Description:
 */
public class ItratorTest {
    public static void main(String[] args) {
        List<String>  list= Lists.newArrayList("a","b","c");
        Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()){
            String next = iterator.next();
//            System.out.println("next==>"+next);
            iterator.remove();
//            System.out.println(next);
        }

        int size = list.size();
        for (int i = 0; i < size; i++) {
            String s = list.get(i);
            list.remove(i);
        }

        HashSet<String> set= Sets.newHashSet("a","b","c");

        set.add("bb");
        Iterator<String> stringIterator = set.iterator();
        while (stringIterator.hasNext()){
            String next = stringIterator.next();
            System.out.println("next==>"+next);
            stringIterator.remove();
        }
    }
}
