package com.ssafy.hanol.examination.controller;

import com.ssafy.hanol.common.response.ResponseFactory;
import com.ssafy.hanol.examination.controller.dto.request.ExaminationRegisterApiRequest;
import com.ssafy.hanol.examination.controller.dto.response.ExaminationRegisterApiResponse;
import com.ssafy.hanol.examination.service.ExaminationService;
import com.ssafy.hanol.examination.service.dto.response.ExaminationRegisterResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("/api/examinations")
@Slf4j
@RequiredArgsConstructor
public class ExaminationController {

    private final ExaminationService examinationService;

    @PostMapping
    public ResponseEntity<?> examinationAdd(@Validated @RequestBody ExaminationRegisterApiRequest request) {
        ExaminationRegisterResponse result = examinationService.addExamination(request.toApplicationDto());
        return ResponseFactory.success("문진 완료", ExaminationRegisterApiResponse.from(result));
    }
}
