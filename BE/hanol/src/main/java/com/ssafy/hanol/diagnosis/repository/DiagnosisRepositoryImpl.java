package com.ssafy.hanol.diagnosis.repository;

import com.ssafy.hanol.diagnosis.domain.Diagnosis;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class DiagnosisRepositoryImpl implements DiagnosisRepository {

    private final JpaDiagnosisRepository jpaDiagnosisRepository;

    @Override
    public Optional<Diagnosis> findById(Long id) {
        return jpaDiagnosisRepository.findById(id);
    }

    @Override
    public Optional<Diagnosis> findTopByMemberIdByIdDesc(Long memberId) {
        return jpaDiagnosisRepository.findTopByMemberIdByIdDesc(memberId);
    }

}
