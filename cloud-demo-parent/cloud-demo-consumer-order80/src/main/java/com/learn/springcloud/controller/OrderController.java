package com.learn.springcloud.controller;


import com.learn.springcloud.entities.CommonResult;
import com.learn.springcloud.entities.Payment;
import com.learn.springcloud.lb.LoadBalancer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;

@RestController
@Slf4j
public class OrderController {

//    private static final String PAYMENT_SEV= "http://localhost:8001";
    //多台服务端 无法确定提供服务的地址 所以此时使用服务名指定
    private static final String PAYMENT_SEV = "http://CLOUD-PAYMENT-SERVICE";

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    private LoadBalancer lb;

    @GetMapping("/consumer/payments")
    public CommonResult<Payment> create(@RequestBody Payment payment){
        log.info("参数 ",payment);
        return restTemplate.postForObject(PAYMENT_SEV +"/payments",payment,CommonResult.class);
    }

    //getObject返回为响应体中对象转化成的json串
    @GetMapping("/consumer/payments/{id}")
    public CommonResult<Payment> get(@PathVariable Long id){
        log.info("查询id为：",id);
        return restTemplate.getForObject(PAYMENT_SEV +"/payments/"+id,CommonResult.class);
    }

    //getForEntity返回responseEntity对象 返回信息更详细 响应头 状态码 响应体
    @GetMapping("/consumer/entity/payments/{id}")
    public CommonResult<Payment> get2(@PathVariable Long id){
        log.info("查询id为：",id);
        ResponseEntity<CommonResult> forEntity = restTemplate.getForEntity(PAYMENT_SEV + "/payments/" + id, CommonResult.class);
        if(forEntity.getStatusCode().is2xxSuccessful()){
            return forEntity.getBody();
        }else {
            return new CommonResult(444,"操作失败");
        }
    }

    @GetMapping("/consumer/payment/lb")
    public String getPaymentLb(){
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        if(instances==null || instances.size()<=0){
            return null;
        }
        ServiceInstance serviceInstance=lb.instances(instances);
        URI uri = serviceInstance.getUri();
        log.info(uri.toString());
        return restTemplate.getForObject(uri+"/payment/lb",String.class);
    }

}
