package com.ssafy.hanol.routine.repository;

import com.ssafy.hanol.routine.domain.MemberRoutine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface JpaMemberRoutineRepository extends JpaRepository<MemberRoutine, Long> {

    @Query("SELECT mr FROM MemberRoutine mr JOIN FETCH mr.routine WHERE mr.member.id = :memberId")
    List<MemberRoutine> findByMemberId(Long memberId);

}
