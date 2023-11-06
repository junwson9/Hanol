package com.ssafy.hanol.examination.repository;

import com.ssafy.hanol.examination.domain.ExaminationResult;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JpaExaminationResultRepository extends JpaRepository<ExaminationResult, Long> {
    Optional<ExaminationResult> findTopByMemberIdOrderByIdDesc(Long memberId);
}
