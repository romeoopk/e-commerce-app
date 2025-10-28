package com.e_commerce_app.payment.dto;

import com.e_commerce_app.payment.entity.Payment;
import com.e_commerce_app.payment.entity.PaymentMethod;

import java.math.BigDecimal;

public record PaymentRequest(
        Integer id,
        BigDecimal amount,
        PaymentMethod paymentMethod,
        Integer orderId,
        String orderReference,
        Customer customer
) {
}
