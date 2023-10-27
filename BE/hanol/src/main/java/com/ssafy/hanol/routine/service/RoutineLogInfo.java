package com.ssafy.hanol.routine.service;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.ssafy.hanol.routine.domain.MemberRoutine;
import com.ssafy.hanol.routine.domain.MemberRoutineLog;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@ToString
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class RoutineLogInfo {

    private Long memberRoutineLogId;
    private Long routineId;
    private String routineName;
    private LocalDate date;
    private Boolean isDone;
    private Long memberRoutineId;
    private Boolean isNotificationActive;
    private LocalTime notificationTime;


    // QueryDsl 조회를 위한 생성자
    public RoutineLogInfo(Long memberRoutineLogId, Long routineId, String routineName, LocalDate date, Boolean isDone, Long memberRoutineId, Boolean isNotificationActive, LocalTime notificationTime) {
        this.memberRoutineLogId = memberRoutineLogId;
        this.routineId = routineId;
        this.routineName = routineName;
        this.date = date;
        this.isDone = isDone;
        this.memberRoutineId = memberRoutineId;
        this.isNotificationActive = isNotificationActive;
        this.notificationTime = notificationTime;
    }

    public RoutineLogInfo(Long memberRoutineLogId, Long routineId, String routineName, LocalDate date, Boolean isDone) {
        this.memberRoutineLogId = memberRoutineLogId;
        this.routineId = routineId;
        this.routineName = routineName;
        this.date = date;
        this.isDone = isDone;
    }


    public static RoutineLogInfo from(MemberRoutineLog memberRoutineLog, MemberRoutine memberRoutine) {
        if(memberRoutine == null) {
            return new RoutineLogInfo(
                    memberRoutineLog.getId(),
                    memberRoutineLog.getRoutine().getId(),
                    memberRoutineLog.getRoutine().getRoutineName(),
                    memberRoutineLog.getDate(),
                    memberRoutineLog.getIsDone()
            );
        } else {
            return new RoutineLogInfo(
                    memberRoutineLog.getId(),
                    memberRoutineLog.getRoutine().getId(),
                    memberRoutineLog.getRoutine().getRoutineName(),
                    memberRoutineLog.getDate(),
                    memberRoutineLog.getIsDone(),
                    memberRoutine.getId(),
                    memberRoutine.getIsNotificationActive(),
                    memberRoutine.getNotificationTime()
            );
        }
    }

}
