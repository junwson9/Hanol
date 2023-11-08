package com.ssafy.hanol.routine.service.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalTime;

@Getter
@Builder
public class MemberRoutineDetailResponse {

    private final Long memberRoutineId;
    private final String routineName;
    private final Boolean isNotificationActive;
    private LocalTime notificationTime;

}
