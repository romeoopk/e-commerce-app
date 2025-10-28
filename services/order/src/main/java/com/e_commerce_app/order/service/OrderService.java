package com.e_commerce_app.order.service;


import com.e_commerce_app.order.customer.CustomerClient;
import com.e_commerce_app.order.dto.*;
import com.e_commerce_app.order.exception.BusinessException;
import com.e_commerce_app.order.kafka.OrderProducer;
import com.e_commerce_app.order.payment.PaymentClient;
import com.e_commerce_app.order.product.ProductClient;
import com.e_commerce_app.order.repository.OrderRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class OrderService {

    private final CustomerClient customerClient;
    private final ProductClient productClient;
    private final OrderRepository orderRepository;
    private final OrderMapper mapper;
    private final OrderLineService orderLineService;
    private final OrderProducer orderProducer;
    private final PaymentClient paymentClient;

    public Integer createOrder(OrderRequest orderRequest) {

        // first check the customer --> OpenFeign

        var customer = customerClient.findCustomerById(orderRequest.customerId()).orElseThrow(() -> new BusinessException("Cannot create order :: " +
                "No Customer Exists with the provided ID :: " + orderRequest.customerId()));

        // purchase the products --> products ms (RestTemplate)
        var purchaseResponses = productClient.purchaseProducts(orderRequest.products());

        // persist order

        var order = orderRepository.save(mapper.toOrder(orderRequest));
        // persist order lines

        for (PurchaseRequest purchaseRequest : orderRequest.products()) {
            orderLineService.saveOrderLine(
                    new OrderLineRequest(null, order.getId(), purchaseRequest.productId(), purchaseRequest.quantity())
            );
        }

        var paymentRequest = new PaymentRequest( orderRequest.amount(), orderRequest.paymentMethod(), orderRequest.id(), orderRequest.reference(), customer);
        paymentClient.requestOrderPayment(paymentRequest);

        // send the order confirmation --> notification ms (kafka)
        orderProducer.sendOrderConfirmation(new OrderConfirmation(
                orderRequest.reference(),
                orderRequest.amount(),
                orderRequest.paymentMethod(),
                customer,
                purchaseResponses
        ));

        return order.getId();

    }

    public List<OrderResponse> findAll() {
        return orderRepository.findAll().stream().map(mapper::fromOrder).toList();
    }

    public OrderResponse findById(Integer orderId) {
        return orderRepository.findById(orderId).map(mapper::fromOrder).orElseThrow(() -> new EntityNotFoundException(String.format("NO order found with the provided ID %d", orderId)));
    }
}
