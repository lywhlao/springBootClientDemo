package com.example.demo;

import com.google.common.util.concurrent.RateLimiter;
import org.junit.Test;

/**
 * @Author: laojiaqi
 * @Date: 2019/6/28 14:43
 * @Description:
 */
public class Guava {


    @Test
    public void TestRateLimit(){
        RateLimiter rateLimiter=RateLimiter.create(1);

        double acquire = rateLimiter.acquire();

    }
}
