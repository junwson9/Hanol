package com.ssafy.hanol.examination.controller;

import com.ssafy.hanol.common.response.ResponseFactory;
import com.ssafy.hanol.examination.controller.dto.request.ExaminationSurveyApiRequest;
import com.ssafy.hanol.examination.controller.dto.response.ExaminationApiResponse;
import com.ssafy.hanol.examination.service.ExaminationService;
import com.ssafy.hanol.global.config.auth.AuthMember;
import com.ssafy.hanol.global.config.auth.AuthenticatedMember;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/examinations")
@Slf4j
@RequiredArgsConstructor
public class ExaminationController {

    private final ExaminationService examinationService;

    @PostMapping
    public ResponseEntity<?> examinationAdd(@Validated @RequestBody ExaminationSurveyApiRequest request,
                                            @AuthenticatedMember AuthMember authMember) {
        log.info("request: {}", request);
        ExaminationApiResponse result = examinationService.addExamination(request, authMember.getId());
        return ResponseFactory.success("문진 완료", result);
    }

    @GetMapping
    public ResponseEntity<?> examinationDetail(@AuthenticatedMember AuthMember authMember) {
        ExaminationApiResponse result = examinationService.findExamination(authMember.getId());
        return ResponseFactory.success("문진 결과 조회 완료", result);
    }
}
