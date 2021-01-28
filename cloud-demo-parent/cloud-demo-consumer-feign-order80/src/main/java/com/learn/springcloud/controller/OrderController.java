package com.learn.springcloud.controller;

import com.learn.springcloud.entities.CommonResult;
import com.learn.springcloud.entities.Payment;
import com.learn.springcloud.service.PaymentFeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {

    @Autowired
    private PaymentFeignService paymentFeignService;

    @GetMapping("consumer/payments/{id}")
    public CommonResult<Payment> get(@PathVariable Long id){
        return paymentFeignService.getPaymentById(id);
    }

    @GetMapping("consumer/payment/timeout")
    public String timeout(){
        return paymentFeignService.timeout();
    }

}
