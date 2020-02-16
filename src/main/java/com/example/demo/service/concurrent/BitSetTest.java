package com.example.demo.service.concurrent;

import com.google.common.collect.Lists;
import javafx.beans.binding.When;

import java.util.BitSet;
import java.util.List;

/**
 *
 * 海量数据找出不重复的数字
 *
 * @Author: laojiaqi
 * @Date: 2020-02-15 13:31
 * @Description:
 */
public class BitSetTest {

    public static void main(String[] args) {
        List<Integer> values = Lists.newArrayList(1, 6, 2, 2, 3, 4, 8);
        BitSet bitSet = new BitSet();
        BitSet dup = new BitSet();
        //构建两个bitSet
        for (int i = 0; i < values.size(); i++) {
            boolean contains = bitSet.get(values.get(i));
            if (contains) {
                dup.set(values.get(i));
            } else {
                bitSet.set(values.get(i));
            }
        }
        //进行异或操作，去除重复数据
        bitSet.xor(dup);
        int index=0;
        while (true) {
            int result = bitSet.nextSetBit(index);
            if(result==-1){
                break;
            }
            System.out.println(result);
            index=result+1;
        }
    }
}
