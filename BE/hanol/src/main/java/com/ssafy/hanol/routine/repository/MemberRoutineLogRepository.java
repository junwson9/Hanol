package com.ssafy.hanol.routine.repository;

import com.ssafy.hanol.routine.domain.MemberRoutineLog;

import java.time.LocalDate;
import java.util.List;

public interface MemberRoutineLogRepository {
    void saveAll(List<MemberRoutineLog> memberRoutineLogs);
    void deleteRoutinesForTodayByRoutineId(Long memberId, List<Long> removedRoutines, LocalDate today);
}
