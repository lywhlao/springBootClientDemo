package com.example.demo.service.jdk;

import java.util.BitSet;

/**
 * @Author: laojiaqi
 * @Date: 2020-02-14 09:16
 * @Description:
 */
public class BitMapTest {

    public static void main(String[] args) {
        BitSet bitSet=new BitSet();
        bitSet.set(1);
        bitSet.set(2);

        int i = bitSet.nextSetBit(0);
        System.out.println(i);
    }
}
