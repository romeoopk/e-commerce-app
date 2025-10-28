package com.e_commerce_app.payment.notification;

import com.e_commerce_app.payment.dto.PaymentConfirmation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationProducer {

    private final KafkaTemplate<String, PaymentConfirmation> kafkaTemplate;

    public void sendNotification(PaymentConfirmation paymentConfirmation) {
        log.info("Sending notification for Payment Confirmation :: {}", paymentConfirmation);
        Message<PaymentConfirmation> message = MessageBuilder.withPayload(paymentConfirmation).setHeader(KafkaHeaders.TOPIC, "payment-topic").build();
        kafkaTemplate.send(message);
    }
}
