package com.ssafy.hanol.notification.domain;

import com.ssafy.hanol.common.model.BaseTimeEntity;
import com.ssafy.hanol.member.domain.Member;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "notification_setting",
        uniqueConstraints = {
            @UniqueConstraint(
                    name = "unique_notification_setting",
                    columnNames = {"member_id"}
            )
        }
        )
public class NotificationSetting extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "notification_setting_id")
    private Long notificationSettingId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @Column(name = "is_check_routine_active", columnDefinition = "TINYINT(1) DEFAULT 0")
    private Boolean isCheckRoutineActive;

    @Column(name = "isIndividualRoutineActive", columnDefinition = "TINYINT(1) DEFAULT 0")
    private Boolean isIndividualRoutineActive;

    @Builder
    public NotificationSetting(Long notificationSettingId, Member member, Boolean isCheckRoutineActive, Boolean isIndividualRoutineActive) {
        this.notificationSettingId = notificationSettingId;
        this.member = member;
        this.isCheckRoutineActive = isCheckRoutineActive;
        this.isIndividualRoutineActive = isIndividualRoutineActive;
    }


    // == 각 알림 활성화 여부 변경 ==
    public void updateIsCheckRoutineActive(Boolean isActive) {
        this.isCheckRoutineActive = isActive;
    }

    public void updateIsIndividualRoutineActive(Boolean isActive) {
        this.isIndividualRoutineActive = isActive;
    }
}
