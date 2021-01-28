package com.learn.springcloud.service.impl;

import com.learn.springcloud.service.PaymentService;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class PaymentServiceImpl implements PaymentService {

    public String paymentInfoSuccess(Integer id) {
        return "线程池"+Thread.currentThread().getName()+"paymentInfo"+id;
    }

    public String paymentInfoFail(Integer id) {
        int time=3;
        try {
            TimeUnit.SECONDS.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "线程池"+Thread.currentThread().getName()+" paymentInfoFail "+ id;
    }
}
