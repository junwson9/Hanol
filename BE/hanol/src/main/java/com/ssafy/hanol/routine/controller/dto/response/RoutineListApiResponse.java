package com.ssafy.hanol.routine.controller.dto.response;


import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.ssafy.hanol.routine.service.dto.response.RoutineListResponse;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class RoutineListApiResponse {

    private List<RoutineListResponse.RoutineItem> myRoutines;
    private List<RoutineListResponse.RoutineItem> suggestedRoutines;

    public RoutineListApiResponse(List<RoutineListResponse.RoutineItem> myRoutines, List<RoutineListResponse.RoutineItem> suggestedRoutines) {
        this.myRoutines = myRoutines;
        this.suggestedRoutines = suggestedRoutines;
    }

    public static RoutineListApiResponse from(RoutineListResponse routineListResponse) {
        return new RoutineListApiResponse(routineListResponse.getMyRoutines(), routineListResponse.getSuggestedRoutines());
    }
}
