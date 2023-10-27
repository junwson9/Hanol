package com.ssafy.hanol.routine.controller;

import com.ssafy.hanol.common.response.ResponseFactory;
import com.ssafy.hanol.routine.controller.dto.request.RoutineAchievementStatusApiRequest;
import com.ssafy.hanol.routine.controller.dto.request.RoutineListModifyApiRequest;
import com.ssafy.hanol.routine.controller.dto.response.RoutineAchievementRatesApiResponse;
import com.ssafy.hanol.routine.controller.dto.response.RoutineAchievementStatusApiResponse;
import com.ssafy.hanol.routine.controller.dto.response.RoutineListApiResponse;
import com.ssafy.hanol.routine.controller.dto.response.RoutineLogListApiResponse;
import com.ssafy.hanol.routine.service.RoutineService;
import com.ssafy.hanol.routine.service.dto.response.RoutineAchievementRatesResponse;
import com.ssafy.hanol.routine.service.dto.response.RoutineAchievementStatusResponse;
import com.ssafy.hanol.routine.service.dto.response.RoutineLogListResponse;
import com.ssafy.hanol.routine.service.dto.response.RoutineListResponse;
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

    @GetMapping
    public ResponseEntity<?> routineList() {
        RoutineListResponse result = routineService.findRoutineList();
        return ResponseFactory.success("루틴 리스트 조회 성공", RoutineListApiResponse.from(result));
    }

    @PatchMapping
    public ResponseEntity<?> routineListModify(@Validated @RequestBody RoutineListModifyApiRequest request) {
        routineService.modifyRoutineList(request.toApplicationDto());
        return ResponseFactory.success("루틴 리스트 변경 성공");
    }

    @GetMapping("/daily-routines")
    public ResponseEntity<?> dailyRoutineLogList(@RequestParam(value = "date") String date) {
        RoutineLogListResponse result = routineService.findMemberRoutineLogByDate(LocalDate.parse(date));
        return ResponseFactory.success("특정일의 루틴 이력 리스트 조회 성공", RoutineLogListApiResponse.from(result));
    }

    @GetMapping("/achievement_rates")
    public ResponseEntity<?> routineAchievementRates(@RequestParam(value = "start-date") String startDate,
                                                     @RequestParam(value = "end-date") String endDate) {
        RoutineAchievementRatesResponse result = routineService.findRoutineAchievementRates(LocalDate.parse(startDate), LocalDate.parse(endDate));
        return ResponseFactory.success("기간 내 일별 루틴 달성률 조회 성공", RoutineAchievementRatesApiResponse.from(result));
    }

    @PatchMapping("/{memberRoutineLogId}/achievement")
    public ResponseEntity<?> routineAchievementStatusModify(@PathVariable Long memberRoutineLogId,
                                                            @Validated @RequestBody RoutineAchievementStatusApiRequest request) {
        RoutineAchievementStatusResponse result = routineService.modifyRoutineAchievementStatus(memberRoutineLogId, request.toApplicationDto());
        return ResponseFactory.success("루틴 달성 여부 변경 완료", RoutineAchievementStatusApiResponse.from(result));
    }



}