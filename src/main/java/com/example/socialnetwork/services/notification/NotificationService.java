package com.example.socialnetwork.services.notification;

import com.example.socialnetwork.models.Notification;
import com.example.socialnetwork.repositories.INotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NotificationService implements INotificationService {
    @Autowired
    private INotificationRepository notificationRepository;

    @Override
    public Optional<Notification> save(Notification object) {
        return Optional.of(notificationRepository.save(object));
    }

    @Override
    public boolean delete(Notification object) {
        try {
            notificationRepository.delete(object);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    @Override
    public List<Notification> findAllOrderDescByUserId(String userId) {
        return notificationRepository.findAllByUser_UserIdOrderByNotificationAtDesc(userId);
    }

    @Override
    public Optional<Notification> findById(Long notificationId) {
        return notificationRepository.findById(notificationId);
    }
}
