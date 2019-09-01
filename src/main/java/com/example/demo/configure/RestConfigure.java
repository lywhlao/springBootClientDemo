package com.example.demo.configure;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestConfigure {


    @Bean
//    @LoadBalanced
    RestTemplate restTemplate(){
        return new RestTemplate();
    }

}
