package com.ssafy.hanol.notification.domain;

import com.ssafy.hanol.common.model.BaseTimeEntity;
import com.ssafy.hanol.member.domain.Member;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "notification_token")
public class NotificationToken extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "notification_token_id")
    private Long notificationTokenId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @Column(name ="device_info")
    private String deviceInfo;

    @Builder
    public NotificationToken(Long notificationTokenId, Member member, String deviceInfo) {
        this.notificationTokenId = notificationTokenId;
        this.member = member;
        this.deviceInfo = deviceInfo;
    }

}
