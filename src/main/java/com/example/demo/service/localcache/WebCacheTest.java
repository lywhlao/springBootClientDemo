package com.example.demo.service.localcache;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.LoadingCache;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @Author: laojiaqi
 * @Date: 2020-02-13 11:34
 * @Description:
 */
@RestController
@Slf4j
public class WebCacheTest {

    LoadingCache<String, String> build = CacheBuilder
            .newBuilder()
            .refreshAfterWrite(1, TimeUnit.SECONDS)
            .build(new RefreshAsyncCacheLoader<String, String>() {
                @Override
                public String load(String key) throws Exception {
                    log.info("{}==>invoke load", Thread.currentThread().getName());
                    return "aa" + RandomUtils.nextInt(1, 10);
                }
            });

    @GetMapping("/cache/test")
    public String test() {
        return build.getUnchecked("aa");
    }
}
