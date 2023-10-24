package com.ssafy.hanol.routine.service.dto.response;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.ssafy.hanol.routine.domain.MemberRoutine;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class RoutineListResponse {

    private List<RoutineItem> myRoutines;
    private List<RoutineItem> suggestedRoutines;

    @Getter
    @Builder
    @JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
    public static class RoutineItem {
        private Long memberRoutineId;
        private Long routineId;
        private String routineName;

        public static RoutineItem from(MemberRoutine memberRoutine) {
            return RoutineItem.builder()
                .memberRoutineId(memberRoutine.getId())
                .routineId(memberRoutine.getRoutine().getId())
                .routineName(memberRoutine.getRoutine().getRoutineName())
                .build();
        }
    }

}
