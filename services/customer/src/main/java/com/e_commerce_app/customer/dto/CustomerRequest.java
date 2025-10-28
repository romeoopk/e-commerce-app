package com.e_commerce_app.customer.dto;

import com.e_commerce_app.customer.Entity.Address;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record CustomerRequest(
        String id,
        @NotNull(message = "Customer firstname is required")
        String firstname,
        @NotNull(message = "Customer lastname is required")
        String lastname,
        @NotNull(message = "Customer email is required")
        @Email(message = "Customer email is not valid")
        String email,
        Address address
) {
}
