package com.ssafy.hanol.routine.repository;

import com.ssafy.hanol.routine.domain.Routine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaRoutineRepository extends JpaRepository<Routine, Long> {
}
