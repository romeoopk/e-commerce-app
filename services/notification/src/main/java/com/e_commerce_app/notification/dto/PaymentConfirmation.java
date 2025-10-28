package com.e_commerce_app.notification.dto;

import java.math.BigDecimal;

public record PaymentConfirmation(
        String orderReference,
        BigDecimal amount,
        PaymentMethod paymentMethod,
        String customerFirstName,
        String customerLastName,
        String customerEmail) {
}
