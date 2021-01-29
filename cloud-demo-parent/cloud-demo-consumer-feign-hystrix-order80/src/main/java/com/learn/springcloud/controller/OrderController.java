package com.learn.springcloud.controller;

import com.learn.springcloud.service.PaymentFeignService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class OrderController {

    @Autowired
    private PaymentFeignService paymentFeignService;

    @GetMapping("/consumer/payment/success/{id}")
    public String paymentSuccess(@PathVariable Integer id){
        return paymentFeignService.success(id);
    }

    @GetMapping("/consumer/payment/fail/{id}")
    public String paymentFail(@PathVariable Integer id){
        return paymentFeignService.fail(id);
    }



}
