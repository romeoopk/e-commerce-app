package com.e_commerce_app.payment.service;

import com.e_commerce_app.payment.dto.PaymentRequest;
import com.e_commerce_app.payment.entity.Payment;
import org.springframework.stereotype.Service;

@Service
public class PaymentMapper {
    public Payment toPayment(PaymentRequest paymentRequest) {
        return Payment.builder().id(paymentRequest.id())
                .orderId(paymentRequest.orderId())
                .paymentMethod(paymentRequest.paymentMethod())
                .amount(paymentRequest.amount())
                .build();
    }
}
