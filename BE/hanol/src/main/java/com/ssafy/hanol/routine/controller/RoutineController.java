package com.ssafy.hanol.routine.controller;

import com.ssafy.hanol.common.response.ResponseFactory;
import com.ssafy.hanol.global.config.auth.AuthMember;
import com.ssafy.hanol.global.config.auth.AuthenticatedMember;
import com.ssafy.hanol.routine.controller.dto.request.RoutineAchievementStatusApiRequest;
import com.ssafy.hanol.routine.controller.dto.request.RoutineListModifyApiRequest;
import com.ssafy.hanol.routine.controller.dto.request.RoutineNotificationModifyApiRequest;
import com.ssafy.hanol.routine.controller.dto.response.*;
import com.ssafy.hanol.routine.service.RoutineService;
import com.ssafy.hanol.routine.service.batch.RoutineBatchScheduler;
import com.ssafy.hanol.routine.service.dto.response.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@Slf4j
@RequestMapping("/api/routines")
@RequiredArgsConstructor
public class RoutineController {

    private final RoutineService routineService;
    private final RoutineBatchScheduler routineBatchScheduler;

    @GetMapping
    public ResponseEntity<?> routineList(@AuthenticatedMember AuthMember authMember) {
        RoutineListResponse result = routineService.findRoutineList(authMember.getId());
        return ResponseFactory.success("루틴 리스트 조회 성공", RoutineListApiResponse.from(result));
    }

    @PatchMapping
    public ResponseEntity<?> routineListModify(@Validated @RequestBody RoutineListModifyApiRequest request,
                                               @AuthenticatedMember AuthMember authMember) {
        routineService.modifyRoutineList(request.toApplicationDto(), authMember.getId());
        return ResponseFactory.success("루틴 리스트 변경 성공");
    }

    @GetMapping("/daily-routine")
    public ResponseEntity<?> dailyRoutineLogList(@RequestParam(value = "date", required = true) String date,
                                                 @AuthenticatedMember AuthMember authMember) {
        RoutineLogListResponse result = routineService.findMemberRoutineLogByDate(LocalDate.parse(date), authMember.getId());
        return ResponseFactory.success("특정일의 루틴 이력 리스트 조회 성공", RoutineLogListApiResponse.from(result));
    }

    @GetMapping("/daily-routine/achievement-rates")
    public ResponseEntity<?> routineAchievementRates(@RequestParam(value = "start-date", required = true) String startDate,
                                                     @RequestParam(value = "end-date", required = true) String endDate,
                                                     @AuthenticatedMember AuthMember authMember) {
        RoutineAchievementRatesResponse result = routineService.findRoutineAchievementRates(LocalDate.parse(startDate), LocalDate.parse(endDate), authMember.getId());
        return ResponseFactory.success("기간 내 일별 루틴 달성률 조회 성공", RoutineAchievementRatesApiResponse.from(result));
    }

    @PatchMapping("/daily-routine/{memberRoutineLogId}/achievement")
    public ResponseEntity<?> routineAchievementStatusModify(@PathVariable Long memberRoutineLogId,
                                                            @Validated @RequestBody RoutineAchievementStatusApiRequest request,
                                                            @AuthenticatedMember AuthMember authMember) {
        RoutineAchievementStatusResponse result = routineService.modifyRoutineAchievementStatus(memberRoutineLogId, request.toApplicationDto(), authMember.getId());
        return ResponseFactory.success("루틴 달성 여부 변경 완료", RoutineAchievementStatusApiResponse.from(result));
    }

    @GetMapping("/{memberRoutineId}")
    public ResponseEntity<?> routineDetails(@PathVariable Long memberRoutineId, @AuthenticatedMember AuthMember authMember) {
        MemberRoutineDetailResponse result = routineService.findMemberRoutineDetail(memberRoutineId, authMember.getId());
        return ResponseFactory.success("루틴 상세 조회 성공", MemberRoutineDetailApiResponse.from(result));
    }

    @PatchMapping("/{memberRoutineId}/notification")
    public ResponseEntity<?> routineNotificationModify(@PathVariable Long memberRoutineId,
                                                       @Validated @RequestBody RoutineNotificationModifyApiRequest request,
                                                       @AuthenticatedMember AuthMember authMember) {
        MemberRoutineDetailResponse result = routineService.modifyRoutineNotification(memberRoutineId, request.toApplicationDto(), authMember.getId());
        return ResponseFactory.success("루틴 알림 설정 변경 완료", MemberRoutineDetailApiResponse.from(result));
    }


    // 데일리 루틴 생성을 위한 테스트 코드
    @PostMapping("/daily-routine/batch")
    public ResponseEntity<?> dailyRoutineBatchRun() {
        routineBatchScheduler.runDailyRoutineJob();
        return ResponseFactory.success("데일리 루틴 생성 배치 실행");
    }

}