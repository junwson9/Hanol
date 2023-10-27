package com.ssafy.hanol.routine.repository;

import com.ssafy.hanol.routine.domain.MemberRoutineLog;
import com.ssafy.hanol.routine.service.RoutineLogInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class MemberRoutineLogRepositoryImpl implements MemberRoutineLogRepository{

    private final JpaMemberRoutineLogRepository jpaMemberRoutineLogRepository;
    private final QueryDslMemberRoutineLogRepository queryDslMemberRoutineLogRepository;

    @Override
    public Optional<MemberRoutineLog> findById(Long id) {
        return jpaMemberRoutineLogRepository.findById(id);
    }

    @Override
    public MemberRoutineLog save(MemberRoutineLog memberRoutineLog) {
        return jpaMemberRoutineLogRepository.save(memberRoutineLog);
    }

    @Override
    public void saveAll(List<MemberRoutineLog> memberRoutineLogs) {
        jpaMemberRoutineLogRepository.saveAll(memberRoutineLogs);
    }

    @Override
    public void deleteRoutinesForTodayByRoutineId(Long memberId, List<Long> removedRoutines, LocalDate today) {
        queryDslMemberRoutineLogRepository.deleteRoutinesForTodayByRoutineId(memberId, removedRoutines, today);
    }

    @Override
    public List<RoutineLogInfo> selectRoutineLogsByMemberIdAndDate(Long memberId, LocalDate date) {
        return queryDslMemberRoutineLogRepository.selectRoutineLogsByMemberIdAndDate(memberId, date);
    }

    @Override
    public Map<LocalDate, Double> computeAchievementRates(Long memberId, LocalDate startOfWeek, LocalDate endOfWeek) {
        return queryDslMemberRoutineLogRepository.computeAchievementRates(memberId, startOfWeek, endOfWeek);
    }
}
