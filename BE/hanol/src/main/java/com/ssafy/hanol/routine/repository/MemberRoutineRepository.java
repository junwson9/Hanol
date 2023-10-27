package com.ssafy.hanol.routine.repository;

import com.ssafy.hanol.routine.domain.MemberRoutine;

import java.util.List;
import java.util.Optional;

public interface MemberRoutineRepository {

    Optional<MemberRoutine> findById(Long id);
    List<MemberRoutine> findByMemberId(Long memberId);
    Optional<MemberRoutine> findByMemberIdAndRoutineId(Long memberId, Long routineId);
    void save(MemberRoutine memberRoutine);
    void saveAll(List<MemberRoutine> memberRoutines);
    void deleteByMemberIdAndRoutineId(Long memberId, Long routineId);
    void deleteByMemberIdAndRoutineId(Long memberId, List<Long> removedRoutineIds);

}
