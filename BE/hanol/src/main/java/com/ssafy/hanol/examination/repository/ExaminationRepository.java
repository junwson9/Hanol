package com.ssafy.hanol.examination.repository;

import com.ssafy.hanol.examination.domain.ExaminationResult;
import com.ssafy.hanol.examination.domain.ExaminationSurvey;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ExaminationRepository {
    ExaminationSurvey saveSurvey(ExaminationSurvey examinationSurvey);

    ExaminationResult saveResult(ExaminationResult examinationResult);

    Optional<ExaminationResult> findResultTopByMemberIdOrderByIdDesc(Long memberId);
}
