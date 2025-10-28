package com.e_commerce_app.order.controller;

import com.e_commerce_app.order.dto.OrderRequest;
import com.e_commerce_app.order.dto.OrderResponse;
import com.e_commerce_app.order.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<Integer> createOrder(@RequestBody @Valid OrderRequest orderRequest) {
        return ResponseEntity.ok(orderService.createOrder(orderRequest));
    }

    @GetMapping
    public ResponseEntity<List<OrderResponse>> findAll() {
        return ResponseEntity.ok(orderService.findAll());
    }

    @GetMapping("/{order-id}")
    public ResponseEntity<OrderResponse> findById(@PathVariable(name = "order-id") Integer orderId) {
        return ResponseEntity.ok(orderService.findById(orderId));
    }


}
