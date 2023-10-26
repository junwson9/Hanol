package com.ssafy.hanol.routine.service.dto.request;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class RoutineListModifyRequest {

    private final List<Long> addedRoutines;
    private final List<Long> removedRoutines;

    @Builder
    public RoutineListModifyRequest(List<Long> addedRoutines, List<Long> removedRoutines) {
        this.addedRoutines = addedRoutines;
        this.removedRoutines = removedRoutines;
    }

}
