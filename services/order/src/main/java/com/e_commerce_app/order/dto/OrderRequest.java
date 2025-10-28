package com.e_commerce_app.order.dto;

import com.e_commerce_app.order.entity.PaymentMethod;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.util.List;

public record OrderRequest(

        Integer id,
        String reference,
        @Positive(message = "Order Amount should be positive")
        BigDecimal amount,
        @NotNull(message = "Payment Method should be precised")
        PaymentMethod paymentMethod,
        @NotNull(message = "Customer should be present")
        @NotEmpty
        @NotBlank
        String customerId,
        @NotEmpty(message = "You should purchase atleast one product")
        List<PurchaseRequest> products
) {


}
