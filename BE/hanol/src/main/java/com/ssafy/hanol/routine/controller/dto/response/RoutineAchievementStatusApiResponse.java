package com.ssafy.hanol.routine.controller.dto.response;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.ssafy.hanol.routine.service.RoutineLogInfo;
import com.ssafy.hanol.routine.service.dto.response.RoutineAchievementStatusResponse;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Map;

@Getter
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class RoutineAchievementStatusApiResponse {

    private RoutineLogInfo updatedRoutineLog;
    private Map<LocalDate, Double> achievementRates;

    public RoutineAchievementStatusApiResponse(RoutineLogInfo updatedRoutineLog, Map<LocalDate, Double> achievementRates) {
        this.updatedRoutineLog = updatedRoutineLog;
        this.achievementRates = achievementRates;
    }

    public static RoutineAchievementStatusApiResponse from(RoutineAchievementStatusResponse routineAchievementStatusResponse) {
        return new RoutineAchievementStatusApiResponse(routineAchievementStatusResponse.getUpdatedRoutineLog(), routineAchievementStatusResponse.getAchievementRates());
    }
}
