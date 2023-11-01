package com.ssafy.hanol.notification.repository;

import com.ssafy.hanol.notification.domain.NotificationToken;

import java.util.Optional;

public interface FCMRepository {
    NotificationToken save(NotificationToken notificationToken);
    Optional<NotificationToken> findByToken(String token);
    void delete(NotificationToken notificationToken);
    void deleteByToken(String fcmToken);
}
