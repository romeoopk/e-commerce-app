package com.e_commerce_app.order.service;

import com.e_commerce_app.order.dto.OrderLineMapper;
import com.e_commerce_app.order.dto.OrderLineRequest;
import com.e_commerce_app.order.dto.OrderLineResponse;
import com.e_commerce_app.order.repository.OrderLineRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class OrderLineService {

    private final OrderLineRepository orderLineRepository;
    private final OrderLineMapper mapper;

    public Integer saveOrderLine(OrderLineRequest orderLineRequest) {
        var order = mapper.toOrderLine(orderLineRequest);
        return orderLineRepository.save(order).getId();
    }

    public List<OrderLineResponse> findAllByOrderId(Integer orderId) {
        return orderLineRepository.findAllById(List.of(orderId)).stream().map(mapper::toOrderLineResponse).toList();
    }
}
