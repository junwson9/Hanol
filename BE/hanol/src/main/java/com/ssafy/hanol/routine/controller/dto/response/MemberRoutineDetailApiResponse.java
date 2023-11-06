package com.ssafy.hanol.routine.controller.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.ssafy.hanol.routine.service.dto.response.MemberRoutineDetailResponse;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Getter
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class MemberRoutineDetailApiResponse {

    private Long memberRoutineId;
    private String routineName;
    private Boolean isNotificationActive;
    @JsonFormat(pattern = "HH:mm:ss")
    private LocalTime notificationTime;

    public MemberRoutineDetailApiResponse(Long memberRoutineId, String routineName, Boolean isNotificationActive, LocalTime notificationTime) {
        this.memberRoutineId = memberRoutineId;
        this.routineName = routineName;
        this.isNotificationActive = isNotificationActive;
        this.notificationTime = notificationTime;
    }

    public static MemberRoutineDetailApiResponse from(MemberRoutineDetailResponse response) {
        return new MemberRoutineDetailApiResponse(response.getMemberRoutineId(), response.getRoutineName(), response.getIsNotificationActive(), response.getNotificationTime());
    }
}
