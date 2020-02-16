package com.example.demo.service.localcache;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.LoadingCache;
import org.apache.commons.lang3.RandomUtils;

import java.util.concurrent.TimeUnit;

/**
 * @Author: laojiaqi
 * @Date: 2020-02-12 22:36
 * @Description:
 */
public class LocalCacheTest {


    public static void main(String[] args) throws InterruptedException {
        RefreshAsyncCacheLoader<String, String> refreshAsyncCacheLoader = new RefreshAsyncCacheLoader<String, String>() {
            @Override
            public String load(String key) throws Exception {

                System.out.println(Thread.currentThread().getName()+"==>invoke load");
                return "aa"+ RandomUtils.nextInt(1,10);
            }
        };
        LoadingCache<String, String> build = CacheBuilder
                .newBuilder()
                .refreshAfterWrite(1, TimeUnit.SECONDS)
                .build(refreshAsyncCacheLoader);

        while (true) {
            String a = build.getUnchecked("a");
            Thread.sleep(1000L);
            System.out.println("=========>"+a);
        }
    }
}
