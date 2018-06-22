package com.example.demo.web;

import com.example.demo.meta.AjaxResult;
import com.example.demo.meta.constent.RetCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class ServiceConsumeController {


    @Value("${spring.application.name}")
    String serviceId;

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    DiscoveryClient discoveryClient;


    //    http://localhost:8080/ajax/service/comsume.do

    @RequestMapping(value = "/ajax/service/comsume.do", method = RequestMethod.GET)
    public AjaxResult service() {
        ResponseEntity<String> forEntity = restTemplate.getForEntity("http://" + serviceId + "/ajax/service/test.do", String.class);
        return new AjaxResult(RetCode.SUCCESS,forEntity.getBody());
    }


}
