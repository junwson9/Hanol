package com.ssafy.hanol.routine.repository;

import com.ssafy.hanol.routine.domain.MemberRoutineLog;
import com.ssafy.hanol.routine.service.RoutineAchievementInfo;
import com.ssafy.hanol.routine.service.RoutineLogInfo;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface MemberRoutineLogRepository {
    Optional<MemberRoutineLog> findById(Long id);
    MemberRoutineLog save(MemberRoutineLog memberRoutineLog);
    void saveAll(List<MemberRoutineLog> memberRoutineLogs);
    void deleteRoutinesForTodayByRoutineId(Long memberId, List<Long> removedRoutines, LocalDate today);
    List<RoutineLogInfo> selectRoutineLogsByMemberIdAndDate(Long memberId, LocalDate date);
    List<RoutineAchievementInfo> findAchievementData(Long memberId, LocalDate startDate, LocalDate endDate);
}
