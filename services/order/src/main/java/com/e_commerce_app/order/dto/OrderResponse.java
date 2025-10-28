package com.e_commerce_app.order.dto;

import com.e_commerce_app.order.entity.PaymentMethod;

import java.math.BigDecimal;

public record OrderResponse (
    Integer id,
    String reference,
    BigDecimal amount,
    PaymentMethod paymentMethod,
    String customerId
){

}
