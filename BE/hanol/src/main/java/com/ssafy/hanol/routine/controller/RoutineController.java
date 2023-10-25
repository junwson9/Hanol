package com.ssafy.hanol.routine.controller;

import com.ssafy.hanol.common.response.ResponseFactory;
import com.ssafy.hanol.routine.controller.dto.request.RoutineListModifyApiRequest;
import com.ssafy.hanol.routine.controller.dto.response.RoutineListApiResponse;
import com.ssafy.hanol.routine.service.RoutineService;
import com.ssafy.hanol.routine.service.dto.response.RoutineListResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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


}