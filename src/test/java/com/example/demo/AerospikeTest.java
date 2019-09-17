package com.example.demo;

import org.junit.Test;

import java.util.BitSet;

/**
 * @Author: laojiaqi
 * @Date: 2019/9/6 14:26
 * @Description:
 */
public class AerospikeTest {


    @Test
    public void test(){
        long n = 12345;
        long n2 = 123456;
        BitSet bs = BitSet.valueOf(new long[]{n});
        BitSet bs2 = BitSet.valueOf(new long[]{n2});
        bs.or(bs2);

        int cardinality = bs.cardinality();

        BitSet bs3 = new BitSet();
        bs3.set(123);
        System.out.println(bs3.cardinality());

    }


}
