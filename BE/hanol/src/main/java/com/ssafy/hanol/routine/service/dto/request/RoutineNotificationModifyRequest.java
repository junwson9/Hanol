package com.ssafy.hanol.routine.service.dto.request;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalTime;

@Getter
public class RoutineNotificationModifyRequest {

    private final Boolean isNotificationActive;
    private final LocalTime notificationTime;

    @Builder
    public RoutineNotificationModifyRequest(Boolean isNotificationActive, LocalTime notificationTime) {
        this.isNotificationActive = isNotificationActive;
        this.notificationTime = notificationTime;
    }
}
