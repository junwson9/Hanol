package com.ssafy.hanol.routine.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.hanol.routine.domain.QRoutine;
import com.ssafy.hanol.routine.service.RoutineInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
@Slf4j
@RequiredArgsConstructor
public class QueryDslRoutineRepository {

    private final JPAQueryFactory jpaQueryFactory;

    public List<RoutineInfo> findByValuesAndNotMemberRoutines(Long memberId, List<Boolean> valueConditions, List<Long> memberRoutineIds) {

        QRoutine routine = QRoutine.routine;

        // valueConditions의 원소가 true인 조건
        List<BooleanExpression> conditions = buildBooleanExpressionByValues(routine, valueConditions);

        // (OR) is_default가 true인 조건 추가
        BooleanExpression finalCondition = routine.isDefault.isTrue()
                .or(Expressions.anyOf(conditions.toArray(new BooleanExpression[0])));

        // (And) 기존 루틴과 중복되지 않는 루틴만 선택
        finalCondition = finalCondition.and(routine.id.notIn(memberRoutineIds));

        List<RoutineInfo> results = jpaQueryFactory
                .select(Projections.constructor(RoutineInfo.class, routine.id, routine.routineName)) // RoutineInfo로 변환하기 위함
                .from(routine)
                .where(finalCondition)
                .fetch();

        return results;
    }


    private List<BooleanExpression> buildBooleanExpressionByValues(QRoutine routine, List<Boolean> valueConditions) {
        List<BooleanExpression> conditions = new ArrayList<>();
        BooleanExpression[] routineValues = {
                routine.isValue1.isTrue(),
                routine.isValue2.isTrue(),
                routine.isValue3.isTrue(),
                routine.isValue4.isTrue(),
                routine.isValue5.isTrue(),
                routine.isValue6.isTrue()
        };

        for (int i = 0; i < valueConditions.size(); i++) {
            if (valueConditions.get(i)) {
                conditions.add(routineValues[i]);
            }
        }
        return conditions;
    }


}
