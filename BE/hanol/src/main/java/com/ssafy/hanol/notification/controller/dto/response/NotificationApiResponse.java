package com.ssafy.hanol.notification.controller.dto.response;


import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.ssafy.hanol.notification.service.dto.response.NotificationResponse;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
@Setter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class NotificationApiResponse {

    private Long notificationConfigurationId;
    private Long memberId;
    private Boolean isCheckRoutineActive;
    private Boolean isIndividualRoutineActive;

    public NotificationApiResponse(Long notificationConfigurationId, Long memberId, Boolean isCheckRoutineActive, Boolean isIndividualRoutineActive) {
        this.notificationConfigurationId = notificationConfigurationId;
        this.memberId = memberId;
        this.isCheckRoutineActive = isCheckRoutineActive;
        this.isIndividualRoutineActive = isIndividualRoutineActive;
    }

    public static NotificationApiResponse from(NotificationResponse notificationResponse) {
        return new NotificationApiResponse(notificationResponse.getNotificationConfigurationId(),
                notificationResponse.getMemberId(),
                notificationResponse.getIsCheckRoutineActive(),
                notificationResponse.getIsIndividualRoutineActive()
        );
    }
}
