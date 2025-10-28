package com.e_commerce_app.order.dto;

import com.e_commerce_app.order.entity.PaymentMethod;

import java.math.BigDecimal;
import java.util.List;

public record OrderConfirmation (
        String orderReference,
        BigDecimal totalAmount,
        PaymentMethod paymentMethod,
        Customer customer,
        List<Product> products
) {
}
