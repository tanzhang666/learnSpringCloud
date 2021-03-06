package com.learn.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient //该注解用于向zookeeper和consul注册中心注册服务
public class ProviderMain8003 {
    public static void main(String[] args) {
        SpringApplication.run(ProviderMain8003.class,args);
    }


}
