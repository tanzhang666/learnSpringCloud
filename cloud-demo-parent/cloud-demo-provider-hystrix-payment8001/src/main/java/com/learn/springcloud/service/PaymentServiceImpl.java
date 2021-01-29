package com.learn.springcloud.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class PaymentServiceImpl implements PaymentService {

    public String paymentInfoSuccess(Integer id) {
        return "线程池"+Thread.currentThread().getName()+"paymentInfo"+id;
    }

    //超时服务降级 返回一个客户端可以接受的fallback结果
    //设置超时峰值3s 超过峰值需要有兜底方法 paymentInfoTimeoutHandler
    //或者方法出现异常
    //hystrix使用单独的线程池进行处理超时
    //兜底方法必须与原方法参数保持一致
    //一般来说 fallback一般放在客户端
    @HystrixCommand(fallbackMethod = "paymentInfoTimeoutHandler",commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value ="4000")
    })
    public String paymentInfoFail(Integer id) {
        int time=3;
        try {
            TimeUnit.SECONDS.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "线程池"+Thread.currentThread().getName()+" paymentInfoFail "+ id;
    }

    public String paymentInfoTimeoutHandler(Integer id){
        return "线程池"+Thread.currentThread().getName()+" 8001系统繁忙 请稍后再试 "+ id;
    }
}
