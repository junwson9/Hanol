package com.ssafy.hanol.routine.repository;

import com.ssafy.hanol.routine.domain.MemberRoutineLog;

import java.util.List;

public interface MemberRoutineLogRepository {
    void saveAll(List<MemberRoutineLog> memberRoutineLogs);
}
