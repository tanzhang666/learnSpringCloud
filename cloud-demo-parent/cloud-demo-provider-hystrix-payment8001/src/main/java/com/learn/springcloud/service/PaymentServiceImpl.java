package com.learn.springcloud.service;

import cn.hutool.core.util.IdUtil;
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

    //开启服务熔断
    //在10秒内需要发送至少10个请求，百分之60的请求失败 会服务先降级再熔断 熔断之后 隔一段时间会慢慢恢复正常
    @HystrixCommand(fallbackMethod = "paymentInfoFailBreakFallBack",commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled",value = "true"),
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value = "10"),
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value = "10000"),
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value = "60"),
    })
    public String paymentInfoFailBreak(Integer id){
        if(id<0) {
            throw new RuntimeException("id 不能为负数");
        }
        String simpleUUID = IdUtil.simpleUUID();
        return Thread.currentThread().getName()+"\t"+"调用成功，流水号："+simpleUUID;
    }

    public String paymentInfoFailBreakFallBack(Integer id){
        return "id 不能为负数 id:"+id;
    }

}
