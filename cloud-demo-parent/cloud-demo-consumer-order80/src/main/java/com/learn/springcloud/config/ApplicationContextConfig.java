package com.learn.springcloud.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ApplicationContextConfig {

//    @LoadBalanced //负载均衡 自定义负载均衡算法不要此注解
    @Bean
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }
//applicationContext.xml <bean id="" class="">




}
