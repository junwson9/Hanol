package com.ssafy.hanol.routine.repository;

import com.ssafy.hanol.routine.domain.Routine;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class RoutineRepositoryImpl implements RoutineRepository {

    private final JpaRoutineRepository jpaRoutineRepository;

    @Override
    public Optional<Routine> findById(Long id) {
        return jpaRoutineRepository.findById(id);
    }
}
