package com.example.demo.web;

import com.example.demo.meta.AjaxResult;
import com.example.demo.service.CouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
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

    @RequestMapping(value = "/ajax/test.do", method = RequestMethod.GET)
    public AjaxResult test() {
        redisTemplate.opsForValue().set("hello", "world");
        Object hello = redisTemplate.opsForValue().get("hello");
//        couponService.addWinner();
        AjaxResult result = new AjaxResult();
        result.setCode(200);
        result.setContent(value+"laojiaqi==>212FD"+"a");
        return result;
//        throw new RuntimeException();
    }

}
