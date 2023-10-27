package com.ssafy.hanol.routine.service.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalTime;

@Getter
@Builder
public class RoutineNotificationModifyResponse {

    private final Long memberRoutineId;
    private final Boolean isNotificationActive;
    private LocalTime notificationTime;

}
