package com.e_commerce_app.order.dto;

import jakarta.persistence.criteria.CriteriaBuilder;

public record OrderLineRequest(
    Integer id,
    Integer orderId,
    Integer productId,
    double quantity) {

}
