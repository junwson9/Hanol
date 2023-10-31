package com.ssafy.hanol.diagnosis.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.hanol.diagnosis.domain.QDiagnosis;
import com.ssafy.hanol.diagnosis.service.DiagnosisInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.querydsl.jpa.JPAExpressions.select;

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
                        diagnosis.createDate
                ))
                .from(diagnosis)
                .where(diagnosis.member.id.eq(memberId))
                .orderBy(diagnosis.createDate.desc());

        if(applyLimit) {
            query = query.limit(limit);
        }

        return query.fetch();
    }
}
