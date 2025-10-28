package com.e_commerce_app.payment.dto;

import com.e_commerce_app.payment.entity.PaymentMethod;

import java.math.BigDecimal;

public record PaymentConfirmation(
        String orderReference,
        BigDecimal amount,
        PaymentMethod paymentMethod,
        String customerFirstName,
        String customerLastName,
        String customerEmail) {
}
