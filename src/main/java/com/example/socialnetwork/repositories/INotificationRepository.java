package com.example.socialnetwork.repositories;

import com.example.socialnetwork.models.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface INotificationRepository extends JpaRepository<Notification, Long> {
    List<Notification> findAllByUser_UserIdOrderByNotificationAtDesc(String userId);
}
