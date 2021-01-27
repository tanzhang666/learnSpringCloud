package com.learn.springcloud.controller;


import com.learn.springcloud.entities.CommonResult;
import com.learn.springcloud.entities.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@Slf4j
public class OrderController {

//    private static final String PAYMENT_SEV= "http://localhost:8001";
    private static final String PAYMENT_SEV = "http://CLOUD-PAYMENT-SERVICE";

    @Autowired
    private RestTemplate restTemplate;


    @GetMapping("/consumer/payments")
    public CommonResult<Payment> create(@RequestBody Payment payment){
        log.info("参数 ",payment);
        return restTemplate.postForObject(PAYMENT_SEV +"/payments",payment,CommonResult.class);
    }

    @GetMapping("/consumer/payments/{id}")
    public CommonResult<Payment> get(@PathVariable Long id){
        log.info("查询id为：",id);
        return restTemplate.getForObject(PAYMENT_SEV +"/payments/"+id,CommonResult.class);
    }


}
