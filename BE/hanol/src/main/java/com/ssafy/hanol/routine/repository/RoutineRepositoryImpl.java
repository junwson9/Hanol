package com.ssafy.hanol.routine.repository;

import com.ssafy.hanol.routine.domain.MemberRoutine;
import com.ssafy.hanol.routine.domain.Routine;
import com.ssafy.hanol.routine.service.RoutineInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class RoutineRepositoryImpl implements RoutineRepository {

    private final JpaRoutineRepository jpaRoutineRepository;
    private final QueryDslRoutineRepository queryDslRoutineRepository;

    @Override
    public Optional<Routine> findById(Long id) {
        return jpaRoutineRepository.findById(id);
    }

    @Override
    public List<Routine> findAllById(List<Long> routines) {
        return jpaRoutineRepository.findAllById(routines);
    }

    @Override
    public List<RoutineInfo> findByValuesAndNotMemberRoutines(Long memberId, List<Integer> values, List<MemberRoutine> memberRoutines) {
        return queryDslRoutineRepository.findByValuesAndNotMemberRoutines(memberId, values, memberRoutines);
    }
}
