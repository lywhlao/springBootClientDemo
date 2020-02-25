package com.example.demo.service.exam.util;

import org.apache.commons.lang3.Validate;

import java.util.Random;

/**
 * @Author: laojiaqi
 * @Date: 2020-02-19 21:59
 * @Description:
 */
public class RandomUtil {

    private static final Random RANDOM = new Random();

    public static int nextInt(int startInclusive, int endExclusive) {
        if (startInclusive == endExclusive) {
            return startInclusive;
        }

        return startInclusive + RANDOM.nextInt(endExclusive - startInclusive);
    }
}
