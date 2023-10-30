package com.ssafy.hanol.notification.repository;

import com.ssafy.hanol.notification.domain.NotificationConfiguration;
import com.ssafy.hanol.notification.service.dto.response.NotificationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class NotificationRepositoryImpl implements NotificationRepository{

    private final JpaNotificationRepository jpaNotificationRepository;
    private final QueryDslNotificationRepository queryDslNotificationRepository;

    @Override
    public Optional<NotificationConfiguration> findByMemberId(Long memberId) {
        return jpaNotificationRepository.findByMemberId(memberId);
    }

    @Override
    public Optional<NotificationResponse> findNotificationResponseByMemberId(Long memberId) {
        return queryDslNotificationRepository.findNotificationResponseByMemberId(memberId);
    }

}
