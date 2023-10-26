package com.ssafy.hanol.routine.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.hanol.routine.domain.QMemberRoutine;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@Slf4j
@RequiredArgsConstructor
public class QueryDslMemberRoutineRepository {

    private final JPAQueryFactory jpaQueryFactory;
    private final EntityManager entityManager;

    public void deleteByMemberIdAndRoutineId(Long memberId, List<Long> removedRoutineIds) {

        QMemberRoutine memberRoutine = QMemberRoutine.memberRoutine;

        BooleanExpression criteria = memberRoutine.member.id.eq(memberId).
                and(memberRoutine.routine.id.in(removedRoutineIds));

        jpaQueryFactory
                .delete(memberRoutine)
                .where(criteria)
                .execute();

        entityManager.flush();
    }
}
