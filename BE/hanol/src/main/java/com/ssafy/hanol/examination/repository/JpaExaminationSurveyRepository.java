package com.ssafy.hanol.examination.repository;

import com.ssafy.hanol.examination.domain.ExaminationSurvey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaExaminationSurveyRepository extends JpaRepository<ExaminationSurvey, Long> {
}
