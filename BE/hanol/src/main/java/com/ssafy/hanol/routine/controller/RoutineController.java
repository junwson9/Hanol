package com.ssafy.hanol.routine.controller;

import com.ssafy.hanol.common.response.ResponseFactory;
import com.ssafy.hanol.routine.controller.dto.response.RoutineListApiResponse;
import com.ssafy.hanol.routine.service.RoutineService;
import com.ssafy.hanol.routine.service.dto.response.RoutineListResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/api/routines")
@RequiredArgsConstructor
public class RoutineController {

    private final RoutineService routineService;

    @GetMapping
    public ResponseEntity<?> routineList() {
        log.info("요청 도착");
        RoutineListResponse result = routineService.findRoutineList();
        log.info("결과 : {}", result);
        return ResponseFactory.success("추천 루틴 리스트 조회 성공", RoutineListApiResponse.from(result));
    }


}