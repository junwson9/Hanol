package com.ssafy.hanol.diagnosis.service;

import com.ssafy.hanol.diagnosis.domain.Diagnosis;
import com.ssafy.hanol.diagnosis.repository.DiagnosisRepository;
import com.ssafy.hanol.diagnosis.service.dto.response.DiagnosisDetailResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class DiagnosisService {

    private final DiagnosisRepository diagnosisRepository;

    public DiagnosisDetailResponse findDiagnosis(Long diagnosisId) {
        // TODO 예외처리: 존재하지 않는 diagnosis, 본인 diagnosis가 아님
        Diagnosis diagnosis = diagnosisRepository.findById(diagnosisId).orElseThrow();
        return DiagnosisDetailResponse.from(DiagnosisInfo.from(diagnosis));
    }

}
