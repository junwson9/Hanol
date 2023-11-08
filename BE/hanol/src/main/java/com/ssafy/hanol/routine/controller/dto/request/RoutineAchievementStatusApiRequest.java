package com.ssafy.hanol.routine.controller.dto.request;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.ssafy.hanol.routine.service.dto.request.RoutineAchievementStatusRequest;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class RoutineAchievementStatusApiRequest {

    @NotNull
    private Boolean isDone;

    public RoutineAchievementStatusRequest toApplicationDto() {
        return RoutineAchievementStatusRequest.builder()
                .isDone(isDone)
                .build();
    }

}
