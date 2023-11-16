package com.ssafy.hanol.routine.service;

import com.ssafy.hanol.common.exception.CustomException;
import com.ssafy.hanol.diagnosis.domain.Diagnosis;
import com.ssafy.hanol.diagnosis.repository.DiagnosisRepository;
import com.ssafy.hanol.member.domain.Member;
import com.ssafy.hanol.member.service.MemberService;
import com.ssafy.hanol.routine.domain.MemberRoutine;
import com.ssafy.hanol.routine.domain.MemberRoutineLog;
import com.ssafy.hanol.routine.domain.Routine;
import com.ssafy.hanol.routine.exception.RoutineErrorCode;
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
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;
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
    private final MemberService memberService;


    // 회원별 설정 루틴 및 추천 루틴 리스트 조회
    public RoutineListResponse findRoutineList(Long memberId) {
        Member member = memberService.findMemberByMemberId(memberId);
        // 1. 회원이 설정해둔 루틴 리스트 조회
        List<MemberRoutine> memberRoutines = memberRoutineRepository.findByMemberId(memberId);

        List<RoutineInfo> myRoutines = memberRoutines.stream()
                .map(RoutineInfo::from)
                .collect(Collectors.toList());

        // 2. 추천 루틴 리스트 조회
        List<Boolean> valueConditions = diagnosisRepository.findTopByMemberIdOrderByIdDesc(memberId)
                .map(diagnosis -> extractValueConditions(diagnosis, 2))
                .orElse(Collections.emptyList());

        // 기존 루틴 id 추출
        List<Long> memberRoutineIds = extractRoutineIds(memberRoutines);

        List<RoutineInfo> suggestedRoutines = routineRepository.findByValuesAndNotMemberRoutines(memberId, valueConditions, memberRoutineIds);

        return RoutineListResponse.builder()
                .myRoutines(myRoutines)
                .suggestedRoutines(suggestedRoutines)
                .build();
    }

    // 루틴 값의 id만 추출하는 메서드
    private List<Long> extractRoutineIds(List<MemberRoutine> memberRoutines) {
        return memberRoutines.stream()
                .map(memberRoutine -> memberRoutine.getRoutine().getId())
                .collect(Collectors.toList());
    }

    // 진단 결과의 valueX 값이 특정 값 이상인 지 여부 확인
    private List<Boolean> extractValueConditions(Diagnosis latestDiagnosis, int threshold) {
        List<Boolean> valueConditions = new ArrayList<>();
        if(latestDiagnosis != null) {
            valueConditions.add(latestDiagnosis.getValue1() >= threshold);
            valueConditions.add(latestDiagnosis.getValue2() >= threshold);
            valueConditions.add(latestDiagnosis.getValue3() >= threshold);
            valueConditions.add(latestDiagnosis.getValue4() >= threshold);
            valueConditions.add(latestDiagnosis.getValue5() >= threshold);
            valueConditions.add(latestDiagnosis.getValue6() >= threshold);
        }
        return valueConditions;
    }


    // 회원별 설정 루틴 리스트 변경
    public void modifyRoutineList(RoutineListModifyRequest routineListModifyRequest, Long memberId) {
        Member member = memberService.findMemberByMemberId(memberId);

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
                        .notificationTime(LocalTime.of(13, 0))
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
    public RoutineLogListResponse findMemberRoutineLogByDate(LocalDate date, Long memberId) {
        Member member = memberService.findMemberByMemberId(memberId);

        List<RoutineLogInfo> routineLogInfos = memberRoutineLogRepository.selectRoutineLogsByMemberIdAndDate(memberId, date);

        return RoutineLogListResponse.builder()
                .dailyRoutines(routineLogInfos)
                .build();
    }


    // 기간 내 일별 루틴 달성률 조회
    public RoutineAchievementRatesResponse findRoutineAchievementRates(LocalDate startDate, LocalDate endDate, Long memberId) {
        Member member = memberService.findMemberByMemberId(memberId);

        Map<LocalDate, Double> achievementRates = computeAchievementRates(memberId, startDate, endDate);

        return RoutineAchievementRatesResponse.builder()
                .achievementRates(achievementRates)
                .build();
    }

    
    // 일별 루틴 달성율 계산
    private Map<LocalDate, Double> computeAchievementRates(Long memberId, LocalDate startDate, LocalDate endDate) {

        Map<LocalDate, Double> achievementRates = new LinkedHashMap<>();

        LocalDate currentDate = startDate;
        while (!currentDate.isAfter(endDate)) {
            achievementRates.put(currentDate, 0.0);
            currentDate = currentDate.plusDays(1);
        }

        List<RoutineAchievementInfo> achievementInfos = memberRoutineLogRepository.findAchievementData(memberId, startDate, endDate);

        for (RoutineAchievementInfo info : achievementInfos) {
            long doneCount = info.getDoneCount();
            long totalCount = info.getTotalCount();
            double rate = totalCount > 0 ? ((double) doneCount / totalCount) * 100 : 0.0;
            achievementRates.put(info.getDate(), rate);
        }

        return achievementRates;
    }


    // 루틴 달성여부 변경
    public RoutineAchievementStatusResponse modifyRoutineAchievementStatus(Long memberRoutineLogId,
                                                                           RoutineAchievementStatusRequest request,
                                                                           Long memberId) {
        MemberRoutineLog routineLog = memberRoutineLogRepository.findById(memberRoutineLogId)
                .orElseThrow(() -> new CustomException(RoutineErrorCode.NOT_FOUNT_ROUTINE_LOG));
        validateMemberAccess(routineLog.getMember().getId(), memberId);

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
        Map<LocalDate, Double> achievementRates = computeAchievementRates(memberId, targetDate, targetDate);

        return RoutineAchievementStatusResponse.builder()
                .updatedRoutineLog(updatedRoutineLog)
                .achievementRates(achievementRates)
                .build();
    }


    // 루틴 알림 설정 변경
    public MemberRoutineDetailResponse modifyRoutineNotification(Long memberRoutineId,
                                                                 RoutineNotificationModifyRequest request,
                                                                 Long memberId) {
        MemberRoutine memberRoutine = memberRoutineRepository.findById(memberRoutineId)
                .orElseThrow(() -> new CustomException(RoutineErrorCode.NOT_FOUND_MEMBER_ROUTINE));
        validateMemberAccess(memberRoutine.getMember().getId(), memberId);

        memberRoutine.updateNotification(request.getIsNotificationActive(), request.getNotificationTime());

        return MemberRoutineDetailResponse.builder()
                .memberRoutineId(memberRoutineId)
                .routineName(memberRoutine.getRoutine().getRoutineName())
                .isNotificationActive(memberRoutine.getIsNotificationActive())
                .notificationTime(memberRoutine.getNotificationTime())
                .build();
    }


    //  회원별 루틴 상세 조회
    public MemberRoutineDetailResponse findMemberRoutineDetail(Long memberRoutineId, Long memberId) {
        MemberRoutine memberRoutine = memberRoutineRepository.findById(memberRoutineId)
                .orElseThrow(() -> new CustomException(RoutineErrorCode.NOT_FOUND_MEMBER_ROUTINE));
        validateMemberAccess(memberRoutine.getMember().getId(), memberId);

        return MemberRoutineDetailResponse.builder()
                .memberRoutineId(memberRoutineId)
                .routineName(memberRoutine.getRoutine().getRoutineName())
                .isNotificationActive(memberRoutine.getIsNotificationActive())
                .notificationTime(memberRoutine.getNotificationTime())
                .build();
    }


    private void validateMemberAccess(Long memberId, Long authMemberId) {
        if(memberId != authMemberId) {
            throw new CustomException(RoutineErrorCode.FORBIDDEN_ACCESS);
        }
    }


}
