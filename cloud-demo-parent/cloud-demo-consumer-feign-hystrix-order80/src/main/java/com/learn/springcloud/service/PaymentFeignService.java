package com.learn.springcloud.service;

import com.learn.springcloud.config.FeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient(value = "cloud-payment-service-hystrix",configuration = FeignConfig.class)
public interface PaymentFeignService {
    @GetMapping("/payment/success/{id}")
    String success(@PathVariable(value = "id") Integer id);

    @GetMapping("/payment/fail/{id}")
    String fail(@PathVariable(value = "id") Integer id);

}
