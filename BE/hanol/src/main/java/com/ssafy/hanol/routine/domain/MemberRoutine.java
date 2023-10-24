package com.ssafy.hanol.routine.domain;

import com.ssafy.hanol.member.domain.Member;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberRoutine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_routine_id")
    private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "routine_id", nullable = false)
    private Routine routine;

    @Column(name = "is_notification_active", columnDefinition = "TINYINT(1) DEFAULT 0")
    private boolean isNotificationActive;

    @Column(name = "notification_time")
    private LocalTime notificationTime;

    @Builder
    public MemberRoutine(Member member, Routine routine, boolean isNotificationActive, LocalTime notificationTime) {
        this.member = member;
        this.routine = routine;
        this.isNotificationActive = isNotificationActive;
        this.notificationTime = notificationTime;
    }

    public void updateNotification(boolean isNotificationActive, LocalTime notificationTime) {
        this.isNotificationActive = isNotificationActive;
        this.notificationTime = notificationTime;
    }
}
