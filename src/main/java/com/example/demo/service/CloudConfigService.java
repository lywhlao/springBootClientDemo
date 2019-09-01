package com.example.demo.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class CloudConfigService {


    @Value("${foo.value}")
    String foo;


    public String getValue(){
        return foo;
    }

}
