package com.ssafy.hanol.notification.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.hanol.notification.domain.QNotificationConfiguration;
import com.ssafy.hanol.notification.service.dto.response.NotificationResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@Slf4j
@RequiredArgsConstructor
public class QueryDslNotificationRepository {

    private final JPAQueryFactory jpaQueryFactory;

    public Optional<NotificationResponse> findNotificationResponseByMemberId(Long memberId) {

        QNotificationConfiguration notification = QNotificationConfiguration.notificationConfiguration;

        NotificationResponse result = jpaQueryFactory
                .select(Projections.constructor(NotificationResponse.class,
                        notification.id,
                        notification.member.id,
                        notification.isCheckRoutineActive,
                        notification.isIndividualRoutineActive
                ))
                .from(notification)
                .where(notification.member.id.eq(memberId))
                .fetchOne();

        return Optional.ofNullable(result);
    }


}
