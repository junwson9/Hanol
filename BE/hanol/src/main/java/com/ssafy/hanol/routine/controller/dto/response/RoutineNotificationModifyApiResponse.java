package com.ssafy.hanol.routine.controller.dto.response;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.ssafy.hanol.routine.service.dto.response.RoutineNotificationModifyResponse;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Getter
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class RoutineNotificationModifyApiResponse {

    private Long memberRoutineId;
    private Boolean isNotificationActive;
    private LocalTime notificationTime;

    public RoutineNotificationModifyApiResponse(Long memberRoutineId, Boolean isNotificationActive, LocalTime notificationTime) {
        this.memberRoutineId = memberRoutineId;
        this.isNotificationActive = isNotificationActive;
        this.notificationTime = notificationTime;
    }

    public static RoutineNotificationModifyApiResponse from(RoutineNotificationModifyResponse response) {
        return new RoutineNotificationModifyApiResponse(response.getMemberRoutineId(), response.getIsNotificationActive(), response.getNotificationTime());
    }
}
