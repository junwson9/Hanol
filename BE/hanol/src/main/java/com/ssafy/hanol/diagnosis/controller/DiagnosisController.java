package com.ssafy.hanol.diagnosis.controller;

import com.ssafy.hanol.common.response.ResponseFactory;
import com.ssafy.hanol.diagnosis.controller.dto.request.DiagnosisApiRequest;
import com.ssafy.hanol.diagnosis.controller.dto.response.DiagnosisDetailApiResponse;
import com.ssafy.hanol.diagnosis.controller.dto.response.DiagnosisListApiResponse;
import com.ssafy.hanol.diagnosis.service.DiagnosisService;
import com.ssafy.hanol.diagnosis.service.dto.response.DiagnosisListResponse;
import com.ssafy.hanol.global.config.auth.AuthMember;
import com.ssafy.hanol.global.config.auth.AuthenticatedMember;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@Slf4j
@RequestMapping("/api/diagnoses")
@RequiredArgsConstructor
public class DiagnosisController {

    private final DiagnosisService diagnosisService;

    @GetMapping("/{diagnosisId}")
    public ResponseEntity<?> diagnosisDetails(@PathVariable Long diagnosisId,
                                              @AuthenticatedMember AuthMember member) {
        DiagnosisDetailApiResponse result = diagnosisService.findDiagnosis(diagnosisId, member.getId());
        return ResponseFactory.success("진단 결과 조회 성공", result);
    }

    @GetMapping
    public ResponseEntity<?> diagnosisList(@RequestParam(value = "limit", required = false) Integer limit,
                                           @AuthenticatedMember AuthMember member) {
        DiagnosisListResponse result = diagnosisService.findDiagnoses(limit, member.getId());
        return ResponseFactory.success("진단 결과 리스트 조회 성공", DiagnosisListApiResponse.from(result));
    }


    @GetMapping("/dates")
    public ResponseEntity<?> diagnosisIdList(@AuthenticatedMember AuthMember member) {
        DiagnosisIdListApiResponse result = diagnosisService.findDiagnosisIds(member.getId());
        return ResponseFactory.success("진단 결과 id 리스트 조회 성공", result);
    }

    @PostMapping
    public ResponseEntity<?> diagnoseImage(@RequestPart MultipartFile file,
                                           @Validated @RequestPart(value = "data") DiagnosisApiRequest diagnosisApiRequest,
                                           @AuthenticatedMember AuthMember member) {

        log.info("image : {}", file.getOriginalFilename());
        log.info("data : {}", diagnosisApiRequest.toString());
        // 진단 이벤트 시작
        diagnosisService.diagnose(diagnosisApiRequest.toDiagnosisRequest(member.getId(), file));

        return ResponseFactory.success("진단 요청 전송 성공");
    }


}
