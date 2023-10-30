package com.ssafy.hanol.diagnosis.repository;

import com.ssafy.hanol.diagnosis.domain.Diagnosis;
import com.ssafy.hanol.diagnosis.service.DiagnosisInfo;
import com.ssafy.hanol.diagnosis.service.dto.response.DiagnosisListResponse;

import java.util.List;
import java.util.Optional;

public interface DiagnosisRepository {

    Optional<Diagnosis> findById(Long id);

    Optional<Diagnosis> findTopByMemberIdOrderByIdDesc(Long memberId);

    List<DiagnosisInfo> findRecentDiagnoses(Long memberId);
}
