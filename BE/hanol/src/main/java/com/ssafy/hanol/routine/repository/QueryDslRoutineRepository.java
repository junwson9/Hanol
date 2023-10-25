package com.ssafy.hanol.routine.repository;

import com.ssafy.hanol.routine.domain.MemberRoutine;
import com.ssafy.hanol.routine.service.RoutineInfo;

import java.util.List;

public interface QueryDslRoutineRepository {

    List<RoutineInfo> findByValuesAndNotMemberRoutines(Long memberId, List<Integer> values, List<MemberRoutine> memberRoutines);
}
