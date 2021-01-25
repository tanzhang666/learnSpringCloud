package com.learn.springcloud.service.impl;

import com.learn.springcloud.dao.PaymentDao;
import com.learn.springcloud.entities.Payment;
import com.learn.springcloud.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired//@Resource也可以 java自带的属性注入
    private PaymentDao paymentDao;


    public int createPayment(Payment payment) {
        return paymentDao.createPayment(payment);
    }

    public Payment getPaymentById(Long id) {
        return paymentDao.getPaymentById(id);
    }
}
