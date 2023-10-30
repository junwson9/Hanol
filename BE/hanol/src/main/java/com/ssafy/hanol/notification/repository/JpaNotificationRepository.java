package com.ssafy.hanol.notification.repository;

import com.ssafy.hanol.notification.domain.NotificationConfiguration;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface JpaNotificationRepository extends JpaRepository<NotificationConfiguration, Long> {
    @Query("SELECT n FROM NotificationConfiguration n WHERE n.member.id = :memberId")
    Optional<NotificationConfiguration> findByMemberId(@Param("memberId") Long memberId);

}
