package com.ssafy.hanol.notification.domain;

import com.ssafy.hanol.common.model.BaseTimeEntity;
import com.ssafy.hanol.member.domain.Member;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "notification_token")
public class NotificationToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "notification_token_id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @Column(name ="device_info", nullable = false)
    private String deviceInfo;

    @Column(name = "token", nullable = false)
    private String token;

    @Column(name = "created_date", updatable = false)
    private LocalDateTime createdDate;

    @Column(name = "refresh_date")
    private LocalDateTime refreshDate;

    @Builder
    public NotificationToken(Member member, String deviceInfo, String token) {
        this.member = member;
        this.deviceInfo = deviceInfo;
        this.token = token;
    }

    @PrePersist
    public void prePersist() {
        this.createdDate = LocalDateTime.now();
    }

    // 정적 팩토리 메서드
    public static NotificationToken createEmpty() {
        return new NotificationToken();
    }

    // 상태 변경 메서드
    public void changeDetails(Member member, String token, String deviceInfo) {
        this.member = member;
        this.token = token;
        this.deviceInfo = deviceInfo;
        this.refreshDate = LocalDateTime.now();
    }

}
