package com.learn.springcloud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class OrderController {

    private static final String INVOKE_URL="http://CLOUD-PAYMENT-SERVICE-CONSUL";

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("consumer/payment/consul")
    public String paymentCon(){
        return restTemplate.getForObject(INVOKE_URL+"/payment/consul",String.class);
    }

}
