package com.ssafy.hanol.notification.service.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
public class NotificationResponse {

    private Long notificationConfigurationId;
    private Long memberId;
    private Boolean isCheckRoutineActive;
    private Boolean isIndividualRoutineActive;

    @Builder
    public NotificationResponse(Long notificationConfigurationId, Long memberId, Boolean isCheckRoutineActive, Boolean isIndividualRoutineActive) {
        this.notificationConfigurationId = notificationConfigurationId;
        this.memberId = memberId;
        this.isCheckRoutineActive = isCheckRoutineActive;
        this.isIndividualRoutineActive = isIndividualRoutineActive;
    }
}
