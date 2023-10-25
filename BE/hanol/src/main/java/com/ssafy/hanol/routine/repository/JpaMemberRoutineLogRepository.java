package com.ssafy.hanol.routine.repository;

import com.ssafy.hanol.routine.domain.MemberRoutineLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaMemberRoutineLogRepository extends JpaRepository<MemberRoutineLog, Long> {
}
