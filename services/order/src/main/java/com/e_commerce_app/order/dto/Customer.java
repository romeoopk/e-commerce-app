package com.e_commerce_app.order.dto;

public record Customer(
        String id,
        String firstname,
        String lastname,
        String email
) {
}
