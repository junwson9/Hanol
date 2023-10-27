package com.ssafy.hanol.routine.service.dto.request;

import lombok.Builder;
import lombok.Getter;

@Getter
public class RoutineAchievementStatusRequest {

    private final Boolean isDone;

    @Builder
    public RoutineAchievementStatusRequest(Boolean isDone) {
        this.isDone = isDone;
    }

}
