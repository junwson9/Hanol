package com.ssafy.hanol.routine.controller.dto.response;


import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.ssafy.hanol.routine.service.RoutineInfo;
import com.ssafy.hanol.routine.service.dto.response.RoutineListResponse;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class RoutineListApiResponse {

    private List<RoutineInfo> myRoutines;
    private List<RoutineInfo> suggestedRoutines;

    public RoutineListApiResponse(List<RoutineInfo> myRoutines, List<RoutineInfo> suggestedRoutines) {
        this.myRoutines = myRoutines;
        this.suggestedRoutines = suggestedRoutines;
    }

    public static RoutineListApiResponse from(RoutineListResponse routineListResponse) {
        return new RoutineListApiResponse(routineListResponse.getMyRoutines(), routineListResponse.getSuggestedRoutines());
    }
}
