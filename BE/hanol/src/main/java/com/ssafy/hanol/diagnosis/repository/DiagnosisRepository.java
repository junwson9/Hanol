package com.ssafy.hanol.diagnosis.repository;

import com.ssafy.hanol.diagnosis.domain.Diagnosis;
import com.ssafy.hanol.diagnosis.service.DiagnosisIdInfo;
import com.ssafy.hanol.diagnosis.service.DiagnosisInfo;

import java.util.List;
import java.util.Optional;

public interface DiagnosisRepository {

    Optional<Diagnosis> findById(Long id);

    Optional<Diagnosis> findTopByMemberIdOrderByIdDesc(Long memberId);

    List<DiagnosisInfo> findDiagnoses(Long memberId, Boolean applyLimit, Integer limit);

    List<DiagnosisIdInfo> findDiagnosisIds(Long memberId);
}
