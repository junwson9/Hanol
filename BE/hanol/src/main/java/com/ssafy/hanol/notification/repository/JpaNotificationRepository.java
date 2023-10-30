package com.ssafy.hanol.notification.repository;

import com.ssafy.hanol.notification.domain.NotificationSetting;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface JpaNotificationRepository extends JpaRepository<NotificationSetting, Long> {
    @Query("SELECT n FROM NotificationSetting n WHERE n.member.id = :memberId")
    Optional<NotificationSetting> findByMemberId(@Param("memberId") Long memberId);

}
