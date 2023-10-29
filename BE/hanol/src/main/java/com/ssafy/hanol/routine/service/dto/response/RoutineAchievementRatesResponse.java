package com.ssafy.hanol.routine.service.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.util.Map;

@Getter
@Builder
public class RoutineAchievementRatesResponse {

    private Map<LocalDate, Double> achievementRates;


}
