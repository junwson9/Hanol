package com.ssafy.hanol.diagnosis.repository;

import com.ssafy.hanol.diagnosis.domain.Diagnosis;
import com.ssafy.hanol.diagnosis.service.DiagnosisInfo;
import com.ssafy.hanol.diagnosis.service.dto.response.DiagnosisListResponse;
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
    public Optional<Diagnosis> findById(Long id) {
        return jpaDiagnosisRepository.findById(id);
    }

    @Override
    public Optional<Diagnosis> findTopByMemberIdOrderByIdDesc(Long memberId) {
        return jpaDiagnosisRepository.findTopByMemberIdOrderByIdDesc(memberId);
    }

    @Override
    public List<DiagnosisInfo> findRecentDiagnoses(Long memberId) {
        return queryDslDiagnosisRepository.findRecentDiagnoses(memberId);
    }

}
