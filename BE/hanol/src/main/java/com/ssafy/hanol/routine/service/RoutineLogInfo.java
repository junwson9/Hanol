package com.ssafy.hanol.routine.service;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@ToString
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class RoutineLogInfo {

    private Long memberRoutineLongId;
    private Long routineId;
    private String routineName;
    private LocalDate date;
    private Boolean isDone;
    private Boolean isNotificationActive;
    private LocalTime notificationTime;


    // QueryDsl 조회를 위한 생성자
    public RoutineLogInfo(Long memberRoutineLongId, Long routineId, String routineName, LocalDate date, Boolean isDone, Boolean isNotificationActive, LocalTime notificationTime) {
        this.memberRoutineLongId = memberRoutineLongId;
        this.routineId = routineId;
        this.routineName = routineName;
        this.date = date;
        this.isDone = isDone;
        this.isNotificationActive = isNotificationActive;
        this.notificationTime = notificationTime;
    }

    public RoutineLogInfo(Long memberRoutineLongId, Long routineId, String routineName, LocalDate date, Boolean isDone) {
        this.memberRoutineLongId = memberRoutineLongId;
        this.routineId = routineId;
        this.routineName = routineName;
        this.date = date;
        this.isDone = isDone;
    }

}
