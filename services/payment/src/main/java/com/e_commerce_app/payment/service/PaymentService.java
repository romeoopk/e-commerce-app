package com.e_commerce_app.payment.service;

import com.e_commerce_app.payment.dto.PaymentConfirmation;
import com.e_commerce_app.payment.dto.PaymentRequest;
import com.e_commerce_app.payment.notification.NotificationProducer;
import com.e_commerce_app.payment.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final PaymentMapper mapper;
    private final NotificationProducer notificationProducer;

    public Integer createPayment(PaymentRequest paymentRequest) {
        var payment = paymentRepository.save(mapper.toPayment(paymentRequest));
        notificationProducer.sendNotification(
                new PaymentConfirmation(
                        paymentRequest.orderReference(),
                        paymentRequest.amount(),
                        paymentRequest.paymentMethod(),
                        paymentRequest.customer().firstname(),
                        paymentRequest.customer().lastname(),
                        paymentRequest.customer().email()));
        return payment.getId();
    }
}
