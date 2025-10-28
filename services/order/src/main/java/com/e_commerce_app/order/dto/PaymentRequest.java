package com.e_commerce_app.order.dto;

import com.e_commerce_app.order.entity.PaymentMethod;

import java.math.BigDecimal;

public record PaymentRequest(
        BigDecimal amount,
        PaymentMethod paymentMethod,
        Integer orderId,
        String orderReference,
        Customer customer) {
}
