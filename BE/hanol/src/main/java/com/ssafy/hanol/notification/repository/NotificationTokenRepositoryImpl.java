package com.ssafy.hanol.notification.repository;

import com.ssafy.hanol.notification.domain.NotificationToken;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class NotificationTokenRepositoryImpl implements NotificationTokenRepository {

    private final JpaNotificationTokenRepository jpaNotificationTokenRepository;

    @Override
    public NotificationToken save(NotificationToken notificationToken) {
        return jpaNotificationTokenRepository.save(notificationToken);
    }

    @Override
    public Optional<NotificationToken> findByToken(String token) {
        return jpaNotificationTokenRepository.findByToken(token);
    }

    @Override
    public void delete(NotificationToken notificationToken) {
        jpaNotificationTokenRepository.delete(notificationToken);
    }

    @Override
    public void deleteByToken(String fcmToken) {
        jpaNotificationTokenRepository.deleteByToken(fcmToken);
    }
}
