package com.ssafy.hanol.examination.repository;

import com.ssafy.hanol.examination.domain.ExaminationResult;
import com.ssafy.hanol.examination.domain.ExaminationSurvey;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ExaminationRepositoryImpl implements ExaminationRepository {

    private final JpaExaminationSurveyRepository jpaExaminationSurveyRepository;
    private final JpaExaminationResultRepository jpaExaminationResultRepository;

    @Override
    public ExaminationSurvey saveSurvey(ExaminationSurvey examinationSurvey) {
        return jpaExaminationSurveyRepository.save(examinationSurvey);
    }

    @Override
    public ExaminationResult saveResult(ExaminationResult examinationResult) {
        return jpaExaminationResultRepository.save(examinationResult);
    }

    @Override
    public Optional<ExaminationResult> findResultTopByMemberIdOrderByIdDesc(Long memberId) {
        return jpaExaminationResultRepository.findTopByMemberIdOrderByIdDesc(memberId);
    }
}
