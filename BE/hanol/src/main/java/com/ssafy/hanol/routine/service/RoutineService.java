package com.ssafy.hanol.routine.service;

import com.ssafy.hanol.diagnosis.domain.Diagnosis;
import com.ssafy.hanol.diagnosis.repository.DiagnosisRepository;
import com.ssafy.hanol.member.domain.Member;
import com.ssafy.hanol.member.repository.MemberRepository;
import com.ssafy.hanol.routine.controller.dto.request.RoutineNotificationModifyApiRequest;
import com.ssafy.hanol.routine.domain.MemberRoutine;
import com.ssafy.hanol.routine.domain.MemberRoutineLog;
import com.ssafy.hanol.routine.domain.Routine;
import com.ssafy.hanol.routine.repository.MemberRoutineLogRepository;
import com.ssafy.hanol.routine.repository.MemberRoutineRepository;
import com.ssafy.hanol.routine.repository.RoutineRepository;
import com.ssafy.hanol.routine.service.dto.request.RoutineAchievementStatusRequest;
import com.ssafy.hanol.routine.service.dto.request.RoutineListModifyRequest;
import com.ssafy.hanol.routine.service.dto.request.RoutineNotificationModifyRequest;
import com.ssafy.hanol.routine.service.dto.response.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class RoutineService {

    private final RoutineRepository routineRepository;
    private final MemberRoutineRepository memberRoutineRepository;
    private final MemberRoutineLogRepository memberRoutineLogRepository;
    private final DiagnosisRepository diagnosisRepository;
    private final MemberRepository memberRepository;


    // 회원별 설정 루틴 및 추천 루틴 리스트 조회
    public RoutineListResponse findRoutineList() {
        // 임시 데이터
        Long memberId = 1L;

        // 회원이 설정해둔 루틴 리스트 조회
        List<MemberRoutine> memberRoutines = memberRoutineRepository.findByMemberId(memberId);

        List<RoutineInfo> myRoutines = memberRoutines.stream()
                .map(RoutineInfo::from)
                .collect(Collectors.toList());

        // 최신 진단 결과 조회
        Diagnosis latestDiagnosis = diagnosisRepository.findTopByMemberIdOrderByIdDesc(memberId).orElse(null);

        // 진단 결과가 존재하면 value 값들을 리스트로 받기
        List<Integer> values = new ArrayList<>();
        if(latestDiagnosis !=  null) {
            values = latestDiagnosis.getValuesAsList();
        }

        // 추천 루틴 조회 : valueX값이 1이상이거나 isDefault=true인 루틴 중, memberRoutines에 없는 루틴.
        List<RoutineInfo> suggestedRoutines = routineRepository.findByValuesAndNotMemberRoutines(memberId, values, memberRoutines);
        log.info("추천 루틴 suggestedRoutines: {}", suggestedRoutines);

        return RoutineListResponse.builder()
                .myRoutines(myRoutines)
                .suggestedRoutines(suggestedRoutines)
                .build();
    }


    // 회원별 설정 루틴 리스트 변경
    public void modifyRoutineList(RoutineListModifyRequest routineListModifyRequest) {
        // 임시 데이터
        Long memberId = 1L;
        Member member = memberRepository.findById(memberId).orElseThrow();

        // TODO 예외 처리: 스케쥴링 작업 중인 경우, 존재하지 않는 루틴, 루틴 추가 시 유니크 제약 조건 위반

        List<Long> removedRoutines = routineListModifyRequest.getRemovedRoutines();
        List<Long> addedRoutines = routineListModifyRequest.getAddedRoutines();
        LocalDate today = LocalDate.now();

        // 루틴 삭제
        if(!removedRoutines.isEmpty()) {
            // 회원별 루틴 테이블에서 삭제
            memberRoutineRepository.deleteByMemberIdAndRoutineId(memberId, removedRoutines);
            // 루틴 이력 테이블에서 삭제
            memberRoutineLogRepository.deleteRoutinesForTodayByRoutineId(memberId, removedRoutines, today);
        }

        // 루틴 추가
        if(!addedRoutines.isEmpty()) {
            List<Routine> routines = routineRepository.findAllById(addedRoutines);

            // 회원별 루틴 테이블에 추가
            List<MemberRoutine> memberRoutines = routines.stream()
                    .map(routine -> MemberRoutine.builder()
                        .member(member)
                        .routine(routine)
                        .isNotificationActive(false)
                        .build())
                    .collect(Collectors.toList());

            memberRoutineRepository.saveAll(memberRoutines);

            // 루틴 이력 테이블에 추가
            List<MemberRoutineLog> memberRoutineLogs = routines.stream()
                    .map(routine -> MemberRoutineLog.builder()
                        .member(member)
                        .routine(routine)
                        .date(today)
                        .isDone(false)
                        .build())
                    .collect(Collectors.toList());

            memberRoutineLogRepository.saveAll(memberRoutineLogs);
        }

    }


    // 날짜별 루틴 이력 리스트 조회
    public RoutineLogListResponse findMemberRoutineLogByDate(LocalDate date) {
        // 임시 데이터
        Long memberId = 1L;

        // TODO 예외 처리: 스케쥴링 작업 중인 경우

        List<RoutineLogInfo> routineLogInfos = memberRoutineLogRepository.selectRoutineLogsByMemberIdAndDate(memberId, date);
        log.info("특정일의 루틴 이력 조회, {}", routineLogInfos);

        return RoutineLogListResponse.builder()
                .dailyRoutines(routineLogInfos)
                .build();
    }


    // 기간 내 일별 루틴 달성률 조회
    public RoutineAchievementRatesResponse findRoutineAchievementRates(LocalDate startDate, LocalDate endDate) {
        // 임시 데이터
        Long memberId = 1L;

        // TODO 예외 처리: 스케쥴링 작업 중인 경우

        log.info("startDate: {}, endDate: {}", startDate, endDate);
        Map<LocalDate, Double> achievementRates = memberRoutineLogRepository.computeAchievementRates(memberId, startDate, endDate);
        return RoutineAchievementRatesResponse.builder()
                .achievementRates(achievementRates)
                .build();
    }
    
    
    // 루틴 달성여부 변경
    public RoutineAchievementStatusResponse modifyRoutineAchievementStatus(Long memberRoutineLogId,
                                                                           RoutineAchievementStatusRequest request) {
        // 임시 데이터
        Long memberId = 1L;

        // TODO 예외 처리: 스케쥴링 작업 중인 경우, 존재하지 않는 루틴

        MemberRoutineLog routineLog = memberRoutineLogRepository.findById(memberRoutineLogId).orElseThrow();
        if(!routineLog.getMember().getId().equals(memberId)) {
            // TODO 예외 처리: 본인이 아님
            log.info("수정 권한이 없습니다");
        }

        // 달성여부 변경
        routineLog.updateDoneStatus(request.getIsDone());

        // 알림 정보가 담긴 RoutineLogInfo 객체 생성
        LocalDate targetDate = routineLog.getDate();
        MemberRoutine memberRoutine = null;
        // 당일인 경우에만 알림 정보 포함
        if(targetDate.isEqual(LocalDate.now())) {
            memberRoutine = memberRoutineRepository.findByMemberIdAndRoutineId(memberId, routineLog.getRoutine().getId()).orElseThrow();
        }
        RoutineLogInfo updatedRoutineLog = RoutineLogInfo.from(routineLog, memberRoutine);

        // 해당일의 달성율 재계산
        Map<LocalDate, Double> achievementRates = memberRoutineLogRepository.computeAchievementRates(memberId, targetDate, targetDate);

        return RoutineAchievementStatusResponse.builder()
                .updatedRoutineLog(updatedRoutineLog)
                .achievementRates(achievementRates)
                .build();
    }

    public RoutineNotificationModifyResponse modifyRoutineNotification(Long memberRoutineId, RoutineNotificationModifyRequest request) {

        // 임시 데이터
        Long memberId = 1L;

        // TODO 예외 처리: 존재하지 않는 루틴
        MemberRoutine memberRoutine = memberRoutineRepository.findById(memberRoutineId).orElseThrow();
        if(!memberRoutine.getMember().getId().equals(memberId)) {
            // TODO 예외 처리: 권한 없음
            log.info("수정 권한이 없습니다");
        }
        memberRoutine.updateNotification(request.getIsNotificationActive(), request.getNotificationTime());

        return RoutineNotificationModifyResponse.builder()
                .memberRoutineId(memberRoutineId)
                .isNotificationActive(memberRoutine.getIsNotificationActive())
                .notificationTime(memberRoutine.getNotificationTime())
                .build();
    }
}
