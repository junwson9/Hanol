package com.ssafy.hanol.diagnosis.controller;

import com.ssafy.hanol.common.response.ResponseFactory;
import com.ssafy.hanol.diagnosis.controller.dto.response.DiagnosisDetailApiResponse;
import com.ssafy.hanol.diagnosis.controller.dto.response.DiagnosisListApiResponse;
import com.ssafy.hanol.diagnosis.service.DiagnosisService;
import com.ssafy.hanol.diagnosis.service.dto.response.DiagnosisDetailResponse;
import com.ssafy.hanol.diagnosis.service.dto.response.DiagnosisListResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/api/diagnoses")
@RequiredArgsConstructor
public class DiagnosisController {

    private final DiagnosisService diagnosisService;

    @GetMapping("/{diagnosisId}")
    public ResponseEntity<?> diagnosisDetails(@PathVariable Long diagnosisId) {
        DiagnosisDetailResponse result = diagnosisService.findDiagnosis(diagnosisId);
        return ResponseFactory.success("진단 결과 조회 성공", DiagnosisDetailApiResponse.from(result));
    }

    @GetMapping("/dashboard")
    public ResponseEntity<?> diagnosisList() {
        DiagnosisListResponse result = diagnosisService.findRecentDiagnoses();
        return ResponseFactory.success("진단 결과 대시보드 조회 성공", DiagnosisListApiResponse.from(result));
    }

}
