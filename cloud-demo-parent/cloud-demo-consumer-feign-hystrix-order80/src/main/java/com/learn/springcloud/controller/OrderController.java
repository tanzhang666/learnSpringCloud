package com.learn.springcloud.controller;

import com.learn.springcloud.service.PaymentFeignService;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@DefaultProperties(defaultFallback = "globalFallbackHandler")//全局fallback
public class OrderController {

    @Autowired
    private PaymentFeignService paymentFeignService;

    @HystrixCommand //出现异常服务降级 但未指定降级方法 会使用全局方法
    @GetMapping("/consumer/payment/success/{id}")
    public String paymentSuccess(@PathVariable Integer id){
        return paymentFeignService.success(id);
    }

    @HystrixCommand(fallbackMethod = "paymentInfoTimeoutHandler",commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value ="2000")
    })
    @GetMapping("/consumer/payment/fail/{id}")
    public String paymentFail(@PathVariable Integer id){
        return paymentFeignService.fail(id);
    }

    public String paymentInfoTimeoutHandler(@PathVariable Integer id){
        return "线程池"+Thread.currentThread().getName()+" 支付系统繁忙 请稍后再试 "+ id;
    }

    public String globalFallbackHandler(){
        return "系统繁忙 请稍后重试";
    }

}
