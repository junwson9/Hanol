package com.ssafy.hanol.routine.service;

import com.ssafy.hanol.routine.domain.MemberRoutine;
import com.ssafy.hanol.routine.domain.MemberRoutineLog;
import com.ssafy.hanol.routine.repository.MemberRoutineLogRepository;
import com.ssafy.hanol.routine.repository.MemberRoutineRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class RoutineSchedulerService {

    private final RedisTemplate<String, Object> redisTemplate;
    private final MemberRoutineLogRepository memberRoutineLogRepository;
    private final MemberRoutineRepository memberRoutineRepository;

    private static final String SCHEDULING_TEMPLATE = "%s_status";

    @Scheduled(cron = "0 0 0 * * ?") // 매일 자정 수행
    public void registerDailyRoutine() {
        String schedulingName = "register_daily_routine";

        try {
            // Redis에 스케쥴링 상태 업데이트
            startScheduling(schedulingName);

            // memberRoutine 테이블을 조회하면서 memberRoutineLog 테이블을 데이터를 Bulk insert 한다
            LocalDate today = LocalDate.now();

            List<MemberRoutine> memberRoutines = memberRoutineRepository.findAll();

            List<MemberRoutineLog> routineLogs = new ArrayList<>();
            for(MemberRoutine routine : memberRoutines) {
                MemberRoutineLog routineLog = MemberRoutineLog.builder()
                        .member(routine.getMember())
                        .routine(routine.getRoutine())
                        .date(today)
                        .isDone(false)
                        .build();
                routineLogs.add(routineLog);
            }
            memberRoutineLogRepository.saveAll(routineLogs);

        } finally {
            // Redis에 스케쥴링 상태 업데이트
            finishedScheduling(schedulingName);
        }
        
    }

    public void startScheduling(String schedulingName) {
        String key = String.format(SCHEDULING_TEMPLATE, schedulingName);
        redisTemplate.opsForValue().set(schedulingName, "STARTED");
    }

    public void finishedScheduling(String schedulingName) {
        String key = String.format(SCHEDULING_TEMPLATE, schedulingName);
        redisTemplate.opsForValue().set(schedulingName, "FINISHED");
    }
}
