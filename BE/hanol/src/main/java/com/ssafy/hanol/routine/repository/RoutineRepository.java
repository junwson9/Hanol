package com.ssafy.hanol.routine.repository;

import com.ssafy.hanol.routine.domain.Routine;
import com.ssafy.hanol.routine.service.RoutineInfo;

import java.util.List;
import java.util.Optional;

public interface RoutineRepository {

    Optional<Routine> findById(Long id);

    List<Routine> findAllById(List<Long> routines);

    List<RoutineInfo> findByValuesAndNotMemberRoutines(Long memberId, List<Boolean> valueConditions, List<Long> memberRoutineIds);
}
