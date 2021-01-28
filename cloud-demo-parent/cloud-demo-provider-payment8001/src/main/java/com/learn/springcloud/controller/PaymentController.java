package com.learn.springcloud.controller;


import com.learn.springcloud.entities.CommonResult;
import com.learn.springcloud.entities.Payment;
import com.learn.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.TimeUnit;

@RestController
@Slf4j
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    //动态指定端口号
    @Value("${server.port}")
    private String serverPort;

    @GetMapping("/payments/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable Long id){
        Payment payment = paymentService.getPaymentById(id);
        log.info("****获取结果"+payment);
        if(payment!=null){
            return new CommonResult<Payment>(200,"获取成功,server.port:"+serverPort,payment);
        }else{
            return new CommonResult(444,"无对应记录，查询ID："+id);
        }

    }

    @PostMapping("/payments")
    public CommonResult createPayment(@RequestBody Payment payment){
        int result = paymentService.createPayment(payment);
        log.info("****插入结果为："+result);
        if(result>0){
            return new CommonResult(200,"插入数据库成功,server.port:"+serverPort,result);
        }else {
            return new CommonResult(444,"插入数据失败");
        }
    }

    @GetMapping("/payment/lb")
    public String lbServer(){
        return serverPort;
    }

    @GetMapping("/payment/timeout")
    public String timeout(){
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return serverPort;
    }

}
