package com.ssafy.hanol.routine.controller.dto.request;

import com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.ssafy.hanol.routine.service.dto.request.RoutineListModifyRequest;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@NoArgsConstructor
@JsonNaming(SnakeCaseStrategy.class)
public class RoutineListModifyApiRequest {

    @NotNull
    private List<Long> addedRoutines;
    @NotNull
    private List<Long> removedRoutines;

    public RoutineListModifyRequest toApplicationDto() {
        return RoutineListModifyRequest.builder()
                .addedRoutines(addedRoutines)
                .removedRoutines(removedRoutines)
                .build();
    }
}
