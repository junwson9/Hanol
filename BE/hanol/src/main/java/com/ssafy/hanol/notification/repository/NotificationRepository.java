package com.ssafy.hanol.notification.repository;

import com.ssafy.hanol.notification.domain.NotificationConfiguration;
import com.ssafy.hanol.notification.service.dto.response.NotificationResponse;

import java.util.Optional;

public interface NotificationRepository {

    Optional<NotificationResponse> findNotificationResponseByMemberId(Long memberId);

    Optional<NotificationConfiguration> findByMemberId(Long memberId);

    NotificationConfiguration save(NotificationConfiguration notificationConfiguration);
}
