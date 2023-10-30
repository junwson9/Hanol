package com.ssafy.hanol.notification.service.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class NotificationResponse {

    private Long notificationSettingId;
    private Long memberId;
    private Boolean isCheckRoutineActive;
    private Boolean isIndividualRoutineActive;

    @Builder
    public NotificationResponse(Long notificationSettingId, Long memberId, Boolean isCheckRoutineActive, Boolean isIndividualRoutineActive) {
        this.notificationSettingId = notificationSettingId;
        this.memberId = memberId;
        this.isCheckRoutineActive = isCheckRoutineActive;
        this.isIndividualRoutineActive = isIndividualRoutineActive;
    }
}
