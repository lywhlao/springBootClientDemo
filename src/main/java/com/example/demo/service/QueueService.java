package com.example.demo.service;

import org.redisson.RedissonDelayedQueue;
import org.redisson.api.RDelayedQueue;
import org.springframework.stereotype.Service;

import java.util.ServiceLoader;

@Service
public class QueueService {

    public void test(){
    }


    public static void getSystemStatistics(){
        ServiceLoader<CouponService> load = ServiceLoader.load(CouponService.class);


    }

}
