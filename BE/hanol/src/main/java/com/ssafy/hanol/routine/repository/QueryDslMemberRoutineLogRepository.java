package com.ssafy.hanol.routine.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.hanol.routine.domain.QMemberRoutineLog;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.util.List;

@Repository
@Slf4j
@RequiredArgsConstructor
public class QueryDslMemberRoutineLogRepository {

    private final JPAQueryFactory jpaQueryFactory;
    private final EntityManager entityManager;

    public void deleteRoutinesForTodayByRoutineId(Long memberId, List<Long> removedRoutines, LocalDate today) {

        QMemberRoutineLog memberRoutineLog = QMemberRoutineLog.memberRoutineLog;

        BooleanExpression criteria = memberRoutineLog.member.id.eq(memberId)
                .and(memberRoutineLog.routine.id.in(removedRoutines));

        jpaQueryFactory
                .delete(memberRoutineLog)
                .where(criteria)
                .execute();

        entityManager.flush();
    }
}
