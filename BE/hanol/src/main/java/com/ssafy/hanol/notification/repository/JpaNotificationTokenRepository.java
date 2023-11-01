package com.ssafy.hanol.notification.repository;

import com.ssafy.hanol.notification.domain.NotificationToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JpaNotificationTokenRepository extends JpaRepository<NotificationToken, Long> {

    Optional<NotificationToken> findByToken(String token);
    void deleteByToken(String token);
}
