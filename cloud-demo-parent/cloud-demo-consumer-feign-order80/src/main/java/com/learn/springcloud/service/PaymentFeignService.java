package com.learn.springcloud.service;

import com.learn.springcloud.config.FeignConfig;
import com.learn.springcloud.entities.CommonResult;
import com.learn.springcloud.entities.Payment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient(value = "cloud-payment-service",configuration = FeignConfig.class)
public interface PaymentFeignService {

    @GetMapping("/payments/{id}")
    CommonResult<Payment> getPaymentById(@PathVariable(value = "id") Long id);

    @GetMapping("/payment/timeout")
    String timeout();
}
