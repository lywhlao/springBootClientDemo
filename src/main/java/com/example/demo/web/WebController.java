package com.example.demo.web;

import com.example.demo.meta.AjaxResult;
import com.example.demo.service.CloudConfigService;
import com.example.demo.service.CouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ConfigurationProperties
public class WebController {

    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    CouponService couponService;

    @Value("${common.value}")
    String value;

    @Autowired
    CloudConfigService configService;

    @Autowired
    DiscoveryClient discoveryClient;

    @RequestMapping(value = "/ajax/test.do", method = RequestMethod.GET)
    public AjaxResult test() {
        redisTemplate.opsForValue().set("hello", "world");
        Object hello = redisTemplate.opsForValue().get("hello");
//        couponService.addWinner();
        AjaxResult result = new AjaxResult();
        result.setCode(200);
        result.setContent("this is test.do");
        return result;
//        throw new RuntimeException();
    }

//    http://localhost:8080/ajax/proxy/test.do

    @RequestMapping(value = "/ajax/proxy/test.do", method = RequestMethod.GET)
    public AjaxResult proxyTest() {
        redisTemplate.opsForValue().set("hello", "world");
        Object hello = redisTemplate.opsForValue().get("hello");
//        couponService.addWinner();
        AjaxResult result = new AjaxResult();
        result.setCode(200);
        result.setContent("this is proxy test.do");
        return result;
//        throw new RuntimeException();
    }

//    http://localhost:8300/ajax/service/test.do

    @RequestMapping(value = "/ajax/service/test.do", method = RequestMethod.GET)
    public AjaxResult service() {

//        List<ServiceInstance> instances = discoveryClient.getInstances("zuul-gateway");
//        ServiceInstance serviceInstance = instances.get(0);
        AjaxResult result = new AjaxResult();
        result.setContent("this is test.do");
        result.setMsg(configService.getValue());
        return result;
    }

}
