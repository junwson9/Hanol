package com.ssafy.hanol.routine.repository;

import com.ssafy.hanol.routine.domain.MemberRoutine;

import java.util.List;
import java.util.Optional;

public interface MemberRoutineRepository {

    Optional<MemberRoutine> findById(Long id);
    List<MemberRoutine> findByMemberId(Long memberId);
    void save(MemberRoutine memberRoutine);
    void saveAll(List<MemberRoutine> memberRoutines);
    void deleteByMemberIdAndRoutineId(Long memberId, Long routineId);

}