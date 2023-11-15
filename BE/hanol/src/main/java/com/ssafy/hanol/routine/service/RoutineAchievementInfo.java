package com.ssafy.hanol.routine.service;

import lombok.Getter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@ToString
public class RoutineAchievementInfo {

    private LocalDate date;
    private Long doneCount;
    private Long totalCount;

    public RoutineAchievementInfo(LocalDate date, Long doneCount, Long totalCount) {
        this.date = date;
        this.doneCount = doneCount;
        this.totalCount = totalCount;
    }
}
