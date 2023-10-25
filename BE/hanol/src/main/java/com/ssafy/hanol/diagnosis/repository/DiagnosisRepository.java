package com.ssafy.hanol.diagnosis.repository;

import com.ssafy.hanol.diagnosis.domain.Diagnosis;

import java.util.Optional;

public interface DiagnosisRepository {

    Optional<Diagnosis> findById(Long id);

    Optional<Diagnosis> findTopByMemberIdOrderByIdDesc(Long memberId);

}
