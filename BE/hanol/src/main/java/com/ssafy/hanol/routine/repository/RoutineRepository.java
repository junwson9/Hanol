package com.ssafy.hanol.routine.repository;

import com.ssafy.hanol.routine.domain.Routine;

import java.util.Optional;

public interface RoutineRepository {

    Optional<Routine> findById(Long id);

}
