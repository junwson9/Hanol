package com.ssafy.hanol.routine.controller.dto.response;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.ssafy.hanol.routine.service.RoutineLogInfo;
import com.ssafy.hanol.routine.service.dto.response.RoutineLogListResponse;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class RoutineLogListApiResponse {

    private List<RoutineLogInfo> dailyRoutines;

    public RoutineLogListApiResponse(List<RoutineLogInfo> dailyRoutines) {
        this.dailyRoutines = dailyRoutines;
    }

    public static RoutineLogListApiResponse from(RoutineLogListResponse routineLogListResponse) {
        return new RoutineLogListApiResponse(routineLogListResponse.getDailyRoutines());
    }
}
