package com.ssafy.hanol.notification.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.hanol.notification.domain.NotificationSetting;
import com.ssafy.hanol.notification.domain.QNotificationSetting;
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

        QNotificationSetting notificationSetting = QNotificationSetting.notificationSetting;

        NotificationResponse result = jpaQueryFactory
                .select(Projections.constructor(NotificationResponse.class,
                        notificationSetting.notificationSettingId,
                        notificationSetting.member.id,
                        notificationSetting.isCheckRoutineActive,
                        notificationSetting.isIndividualRoutineActive
                ))
                .from(notificationSetting)
                .where(notificationSetting.member.id.eq(memberId))
                .fetchOne();

        return Optional.ofNullable(result);
    }


}
