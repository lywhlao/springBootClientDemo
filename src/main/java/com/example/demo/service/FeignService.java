package com.example.demo.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 */
//注册服务serviceid
@FeignClient(value = "hello-service")
public interface FeignService {

    //服务url
    @RequestMapping("/ajax/service/test.do")
    String hello();
}