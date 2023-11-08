package com.ssafy.hanol.routine.service.dto.response;

import com.ssafy.hanol.routine.service.RoutineLogInfo;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.util.Map;

@Getter
@Builder
public class RoutineAchievementStatusResponse {

    private RoutineLogInfo updatedRoutineLog;
    private Map<LocalDate, Double> achievementRates;


}
