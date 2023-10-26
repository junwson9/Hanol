package com.ssafy.hanol.routine.service;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.ssafy.hanol.routine.domain.MemberRoutine;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class RoutineInfo {
    private Long memberRoutineId;
    private Long routineId;
    private String routineName;

    // QueryDsl 이용을 위한 생성자 추가 (파라미터 순서 변경 시 QueryDsl도 수정 필요)
    public RoutineInfo(Long routineId, String routineName) {
        this.routineId = routineId;
        this.routineName = routineName;
    }

    @Builder
    public RoutineInfo(Long memberRoutineId, Long routineId, String routineName) {
        this.memberRoutineId = memberRoutineId;
        this.routineId = routineId;
        this.routineName = routineName;
    }

    public static RoutineInfo from(MemberRoutine memberRoutine) {
        return RoutineInfo.builder()
                .memberRoutineId(memberRoutine.getId())
                .routineId(memberRoutine.getRoutine().getId())
                .routineName(memberRoutine.getRoutine().getRoutineName())
                .build();
    }
}
