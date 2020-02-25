package com.example.demo.web;

import cn.tongdun.fpguangfa.client.entity.BlackboxResultEntity;
import cn.tongdun.fpguangfa.client.service.BlackboxFacadeService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.meta.AjaxResult;
import com.example.demo.service.CloudConfigService;
import com.example.demo.service.CouponService;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.protocol.HTTP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

@RestController
@Slf4j
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
    HttpServletRequest request;
//    @Autowired
//    DiscoveryClient discoveryClient;

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

    @RequestMapping(value = {"/ok","/"}, method = RequestMethod.GET)
    public String ok(@RequestParam(required = false) String a, HttpServletResponse response) {
        log.info("a ==>{}",a);

        BlackboxResultEntity aaa = BlackboxFacadeService.decodeBlackBox("aaa", "", false);
        return  JSON.toJSONString(aaa);


//        Enumeration<String> headerNames = request.getHeaderNames();
////        Map<String,Object> headerValue=new HashMap<>();
////        while(headerNames.hasMoreElements()){
////            String headerName = headerNames.nextElement();
////            headerValue.put(headerName,request.getHeader(headerName));
////        }
////        log.info("header value==>{}", JSON.toJSONString(headerValue));
//        JSONObject json=new JSONObject();
//        ((JSONObject) json).put("helo","+ok");
//        response.setHeader(HTTP.CONTENT_TYPE,"text/plain;charset=ISO-8859-1");
//        return json.toJSONString();
    }

}
