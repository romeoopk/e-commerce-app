package com.e_commerce_app.notification.consumer;

import com.e_commerce_app.notification.dto.OrderConfirmation;
import com.e_commerce_app.notification.dto.PaymentConfirmation;
import com.e_commerce_app.notification.entity.Notification;
import com.e_commerce_app.notification.entity.NotificationType;
import com.e_commerce_app.notification.repository.NotificationRepository;
import com.e_commerce_app.notification.service.EmailService;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationConsumer {

    private final NotificationRepository notificationRepository;
    private final EmailService emailService;

    @KafkaListener(topics = "payment-topic")
    public void consumePaymentSuccessNotification(PaymentConfirmation paymentConfirmation) throws MessagingException {
        log.info(String.format("Consuming the message from payment-topic Topic %s ", paymentConfirmation));
        notificationRepository.save(Notification.builder()
                .notificationType(NotificationType.PAYMENT_CONFIRMATION)
                .notificationDate(LocalDateTime.now())
                .paymentConfirmation(paymentConfirmation)
                .build());

        var customerName = paymentConfirmation.customerFirstName() + " " + paymentConfirmation.customerLastName();
        emailService.sendPaymentSuccessEmail(paymentConfirmation.customerEmail(), customerName, paymentConfirmation.amount(), paymentConfirmation.orderReference());
    }

    @KafkaListener(topics = "order-topic")
    public void consumeOrderSuccessNotification(OrderConfirmation orderConfirmation) throws MessagingException {
        log.info(String.format("Consuming the message from order-topic Topic %s ", orderConfirmation));
        notificationRepository.save(Notification.builder()
                .notificationType(NotificationType.ORDER_CONFIRMATION)
                .notificationDate(LocalDateTime.now())
                .orderConfirmation(orderConfirmation)
                .build());

        var customerName = orderConfirmation.customer().firstname() + " " + orderConfirmation.customer().lastname();
        emailService.sendOrderConfirmationEmail(orderConfirmation.customer().email(), customerName, orderConfirmation.totalAmount(), orderConfirmation.orderReference(), orderConfirmation.products());

    }

}
