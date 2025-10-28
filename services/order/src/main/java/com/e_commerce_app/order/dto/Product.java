package com.e_commerce_app.order.dto;

import java.math.BigDecimal;

public record Product(
        Integer productId,
        String name,
        String description,
        BigDecimal price,
        double quantity) {
}
