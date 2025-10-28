package com.e_commerce_app.customer.dto;

import com.e_commerce_app.customer.Entity.Address;

public record CustomerResponse(
        String id,
        String firstname,
        String lastname,
        String email,
        Address address
) {
}
