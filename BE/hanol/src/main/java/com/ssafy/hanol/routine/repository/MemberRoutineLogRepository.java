package com.ssafy.hanol.routine.repository;

import com.ssafy.hanol.routine.domain.MemberRoutineLog;
import com.ssafy.hanol.routine.service.RoutineLogInfo;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface MemberRoutineLogRepository {
    void saveAll(List<MemberRoutineLog> memberRoutineLogs);
    void deleteRoutinesForTodayByRoutineId(Long memberId, List<Long> removedRoutines, LocalDate today);
    List<RoutineLogInfo> selectRoutineLogsByMemberIdAndDate(Long memberId, LocalDate date);
    Map<LocalDate, Double> computeAchievementRates(Long memberId, LocalDate startOfWeek, LocalDate endOfWeek);
}
