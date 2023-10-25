package com.ssafy.hanol.routine.repository;

import com.ssafy.hanol.routine.domain.MemberRoutineLog;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class MemberRoutineLogRepositoryImpl implements MemberRoutineLogRepository{

    private final JpaMemberRoutineLogRepository jpaMemberRoutineLogRepository;

    @Override
    public void saveAll(List<MemberRoutineLog> memberRoutineLogs) {
        jpaMemberRoutineLogRepository.saveAll(memberRoutineLogs);
    }
}
