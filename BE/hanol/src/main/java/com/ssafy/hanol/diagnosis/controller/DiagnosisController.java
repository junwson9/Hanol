package com.ssafy.hanol.diagnosis.controller;

import com.ssafy.hanol.diagnosis.service.DiagnosisService;
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
        return null;
    }

}
