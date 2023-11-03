package com.ssafy.hanol.notification.domain;

import com.ssafy.hanol.common.model.BaseTimeEntity;
import com.ssafy.hanol.member.domain.Member;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "notification_configuration",
        uniqueConstraints = {
            @UniqueConstraint(
                    name = "unique_notification_configuration",
                    columnNames = {"member_id"}
            )
        }
        )
public class NotificationConfiguration extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "notification_configuration_id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @Column(name = "is_check_routine_active", columnDefinition = "TINYINT(1) DEFAULT 0")
    private Boolean isCheckRoutineActive;

    @Column(name = "is_individual_routine_active", columnDefinition = "TINYINT(1) DEFAULT 0")
    private Boolean isIndividualRoutineActive;

    @Builder
    public NotificationConfiguration(Long id, Member member, Boolean isCheckRoutineActive, Boolean isIndividualRoutineActive) {
        this.id = id;
        this.member = member;
        this.isCheckRoutineActive = isCheckRoutineActive;
        this.isIndividualRoutineActive = isIndividualRoutineActive;
    }


    // == 회원가입 시 알림 설정 데이터 생성 ==
    public static NotificationConfiguration fromMember(Member member) {
        return NotificationConfiguration.builder()
                .member(member)
                .isCheckRoutineActive(true)
                .isIndividualRoutineActive(true)
                .build();
    }

    // == 각 알림 활성화 여부 변경 ==
    public void changeIsCheckRoutineActive(Boolean isActive) {
        this.isCheckRoutineActive = isActive;
    }

    public void changeIsIndividualRoutineActive(Boolean isActive) {
        this.isIndividualRoutineActive = isActive;
    }
}
