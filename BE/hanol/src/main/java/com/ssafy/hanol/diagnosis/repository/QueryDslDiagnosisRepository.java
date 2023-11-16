package com.ssafy.hanol.diagnosis.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.hanol.diagnosis.domain.Diagnosis;
import com.ssafy.hanol.diagnosis.domain.QDiagnosis;
import com.ssafy.hanol.diagnosis.service.DiagnosisIdInfo;
import com.ssafy.hanol.diagnosis.service.DiagnosisInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Slf4j
@RequiredArgsConstructor
public class QueryDslDiagnosisRepository {

    private final JPAQueryFactory jpaQueryFactory;

    // 최근 진단 데이터 20개를 조회하는 쿼리
    public List<DiagnosisInfo> findDiagnoses(Long memberId, Boolean applyLimit, Integer limit) {

        QDiagnosis diagnosis = QDiagnosis.diagnosis;

        JPAQuery<DiagnosisInfo> query = jpaQueryFactory.select(
                        Projections.constructor(DiagnosisInfo.class,
                        diagnosis.id,
                        diagnosis.member.id,
                        diagnosis.value1, diagnosis.value2, diagnosis.value3,
                        diagnosis.value4, diagnosis.value5, diagnosis.value6,
                        diagnosis.imageUrl,
                        diagnosis.deviceType,
                        diagnosis.scanPart,
                        diagnosis.createdDate
                ))
                .from(diagnosis)
                .where(diagnosis.member.id.eq(memberId)
                        .and(diagnosis.value1.ne(-1)))
                .orderBy(diagnosis.createdDate.desc());

        if(applyLimit) {
            query = query.limit(limit);
        }

        return query.fetch();
    }

    public List<DiagnosisIdInfo> findDiagnosisIds(Long memberId) {

        QDiagnosis diagnosis = QDiagnosis.diagnosis;

        List<DiagnosisIdInfo> result = jpaQueryFactory.select(
                        Projections.constructor(DiagnosisIdInfo.class,
                                diagnosis.id,
                                diagnosis.createdDate
                        ))
                .from(diagnosis)
                .where(diagnosis.member.id.eq(memberId)
                        .and(diagnosis.value1.ne(-1)))
                .orderBy(diagnosis.createdDate.desc())
                .fetch();

        return result;
    }

    public Optional<Diagnosis> findTopByMemberIdOrderByIdDesc(Long memberId) {

        QDiagnosis diagnosis = QDiagnosis.diagnosis;
        Diagnosis result = jpaQueryFactory.selectFrom(diagnosis)
                .where(
                        diagnosis.member.id.eq(memberId)
                                .and(diagnosis.value1.ne(-1))
                )
                .orderBy(diagnosis.id.desc())
                .fetchFirst();

        return Optional.ofNullable(result);
    }
}
