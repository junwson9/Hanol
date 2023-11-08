/**
 * 매일 자정 MemberRoutineLog 데이터를 삽입하는 스케쥴러
 * Spring Batch(RoutineBatchScheduler.class)로 대체하여 스케쥴링 중단
 */

package com.ssafy.hanol.routine.service.batch;

import com.ssafy.hanol.common.util.SchedulingStatusUtil;
import com.ssafy.hanol.routine.domain.MemberRoutine;
import com.ssafy.hanol.routine.domain.MemberRoutineLog;
import com.ssafy.hanol.routine.repository.MemberRoutineLogRepository;
import com.ssafy.hanol.routine.repository.MemberRoutineRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
@RequiredArgsConstructor
public class RoutineScheduler {
    private final SchedulingStatusUtil schedulingStatusUtil;
    private final MemberRoutineLogRepository memberRoutineLogRepository;
    private final MemberRoutineRepository memberRoutineRepository;

//    @Scheduled(cron = "0 0 0 * * ?") // 매일 자정 수행
    @Transactional
    public void registerDailyRoutine() {
        LocalDate today = LocalDate.now();
        String schedulingName = "register_daily_routine";
        log.info("루틴 입력 스케쥴링 시작: {}", today);

        try {
            // Redis에 스케쥴링 상태 업데이트
            schedulingStatusUtil.startScheduling(schedulingName);

            // memberRoutine 테이블을 조회하여 memberRoutineLog 테이블에 데이터를 Bulk Insert 한다
            List<MemberRoutine> memberRoutines = memberRoutineRepository.findAll();

            List<MemberRoutineLog> routineLogs = new ArrayList<>();
            for (MemberRoutine routine : memberRoutines) {
                MemberRoutineLog routineLog = MemberRoutineLog.builder()
                        .member(routine.getMember())
                        .routine(routine.getRoutine())
                        .date(today)
                        .isDone(false)
                        .build();
                routineLogs.add(routineLog);
            }
            memberRoutineLogRepository.saveAll(routineLogs);

        } catch (Exception e) {
            log.error("루틴 입력 스케쥴링 중 에러 발생: {}", e.getMessage(), e);

        } finally {
            // Redis에 스케쥴링 상태 업데이트
            schedulingStatusUtil.finishedScheduling(schedulingName);
            log.info("루틴 입력 스케쥴링 종료: {}", today);
        }
    }
}
