package com.e_commerce_app.notification.repository;

import com.e_commerce_app.notification.entity.Notification;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationRepository extends MongoRepository<Notification, Integer> {
}
