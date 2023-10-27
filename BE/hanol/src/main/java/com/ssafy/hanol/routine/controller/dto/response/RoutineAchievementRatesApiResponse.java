package com.ssafy.hanol.routine.controller.dto.response;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.ssafy.hanol.routine.service.dto.response.RoutineAchievementRatesResponse;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Map;

@Getter
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class RoutineAchievementRatesApiResponse {

    private Map<LocalDate, Double> achievementRates;

    public RoutineAchievementRatesApiResponse(Map<LocalDate, Double> achievementRates) {
        this.achievementRates = achievementRates;
    }

    public static RoutineAchievementRatesApiResponse from(RoutineAchievementRatesResponse routineAchievementRatesResponse) {
        return new RoutineAchievementRatesApiResponse(routineAchievementRatesResponse.getAchievementRates());
    }
}
