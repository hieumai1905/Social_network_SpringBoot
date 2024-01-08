package com.example.socialnetwork.services.notification;

import com.example.socialnetwork.models.Notification;
import com.example.socialnetwork.services.IGeneralService;

import java.util.List;
import java.util.Optional;

public interface INotificationService extends IGeneralService<Notification, Long> {
    List<Notification> findAllOrderDescByUserId(String userId);

    Optional<Notification> findById(Long notificationId);
}
