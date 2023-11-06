package com.ssafy.hanol.diagnosis.repository;

import com.ssafy.hanol.diagnosis.domain.Diagnosis;
import com.ssafy.hanol.diagnosis.service.DiagnosisIdInfo;
import com.ssafy.hanol.diagnosis.service.DiagnosisInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class DiagnosisRepositoryImpl implements DiagnosisRepository {

    private final JpaDiagnosisRepository jpaDiagnosisRepository;
    private final QueryDslDiagnosisRepository queryDslDiagnosisRepository;


    @Override
    public Diagnosis save(Diagnosis diagnosis) {
        return jpaDiagnosisRepository.save(diagnosis);
    }

    @Override
    public Optional<Diagnosis> findById(Long id) {
        return jpaDiagnosisRepository.findById(id);
    }

    @Override
    public Optional<Diagnosis> findTopByMemberIdOrderByIdDesc(Long memberId) {
        return jpaDiagnosisRepository.findTopByMemberIdOrderByIdDesc(memberId);
    }

    @Override
    public List<DiagnosisInfo> findDiagnoses(Long memberId, Boolean applyLimit, Integer limit) {
        return queryDslDiagnosisRepository.findDiagnoses(memberId, applyLimit, limit);
    }

    @Override
    public List<DiagnosisIdInfo> findDiagnosisIds(Long memberId) {
        return queryDslDiagnosisRepository.findDiagnosisIds(memberId);
    }

}
