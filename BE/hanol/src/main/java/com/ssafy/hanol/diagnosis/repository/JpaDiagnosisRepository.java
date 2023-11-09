package com.ssafy.hanol.diagnosis.repository;

import com.ssafy.hanol.diagnosis.domain.Diagnosis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface JpaDiagnosisRepository extends JpaRepository<Diagnosis, Long> {

}
