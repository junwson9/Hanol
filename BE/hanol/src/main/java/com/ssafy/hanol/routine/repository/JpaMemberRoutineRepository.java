package com.ssafy.hanol.routine.repository;

import com.ssafy.hanol.routine.domain.MemberRoutine;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JpaMemberRoutineRepository extends JpaRepository<MemberRoutine, Long> {
    List<MemberRoutine> findByMemberId(Long memberId);

}
