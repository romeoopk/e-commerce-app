package com.e_commerce_app.payment.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;

@Validated
public record Customer(
    String id,
    @NotNull(message = "Firstname is required")
    String firstname,
    @NotNull(message = "Lastname is required")
    String lastname,
    @Email(message = "Customer email is not valid")
    @NotNull(message = "Email is required")
    String email) {
}
