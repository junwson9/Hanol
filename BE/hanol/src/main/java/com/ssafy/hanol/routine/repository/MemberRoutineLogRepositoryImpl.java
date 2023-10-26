package com.ssafy.hanol.routine.repository;

import com.ssafy.hanol.routine.domain.MemberRoutineLog;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
@RequiredArgsConstructor
public class MemberRoutineLogRepositoryImpl implements MemberRoutineLogRepository{

    private final JpaMemberRoutineLogRepository jpaMemberRoutineLogRepository;
    private final QueryDslMemberRoutineLogRepository queryDslMemberRoutineLogRepository;

    @Override
    public void saveAll(List<MemberRoutineLog> memberRoutineLogs) {
        jpaMemberRoutineLogRepository.saveAll(memberRoutineLogs);
    }

    @Override
    public void deleteRoutinesForTodayByRoutineId(Long memberId, List<Long> removedRoutines, LocalDate today) {
        queryDslMemberRoutineLogRepository.deleteRoutinesForTodayByRoutineId(memberId, removedRoutines, today);
    }
}
