package com.example.demo.web;

import com.example.demo.meta.AjaxResult;
import com.example.demo.service.CouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WebController {

    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    CouponService couponService;

    @RequestMapping(value = "/ajax/test.do", method = RequestMethod.GET)
    public AjaxResult test() {
        redisTemplate.opsForValue().set("hello", "world");
        Object hello = redisTemplate.opsForValue().get("hello");
//        couponService.addWinner();
        AjaxResult result = new AjaxResult();
        result.setCode(200);
        result.setContent(hello);
//        return result;
        throw new RuntimeException();
    }

}
