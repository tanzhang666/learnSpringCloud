package com.learn.springcloud.service;

import com.learn.springcloud.entities.Payment;

public interface PaymentService {
    int createPayment(Payment payment);
    Payment getPaymentById(Long id);
}
