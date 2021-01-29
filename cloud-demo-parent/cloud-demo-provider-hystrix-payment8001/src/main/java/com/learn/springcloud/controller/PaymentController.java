package com.learn.springcloud.controller;

import com.learn.springcloud.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    @GetMapping("/payment/success/{id}")
    public String success(@PathVariable Integer id){
        return paymentService.paymentInfoSuccess(id);
    }

    @GetMapping("/payment/fail/{id}")
    public String fail(@PathVariable Integer id){
        return paymentService.paymentInfoFail(id);
    }

    @GetMapping("/payment/break/{id}")
    public String circuitBreak(@PathVariable Integer id)
    {
        return paymentService.paymentInfoFailBreak(id);
    }

}
