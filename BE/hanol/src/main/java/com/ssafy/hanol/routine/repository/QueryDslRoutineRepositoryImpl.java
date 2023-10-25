package com.ssafy.hanol.routine.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.hanol.routine.domain.MemberRoutine;
import com.ssafy.hanol.routine.domain.QMemberRoutine;
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
public class QueryDslRoutineRepositoryImpl implements QueryDslRoutineRepository {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<RoutineInfo> findByValuesAndNotMemberRoutines(Long memberId, List<Integer> values, List<MemberRoutine> memberRoutines) {

        QRoutine routine = QRoutine.routine;

        // values의 원소 value 값이 1이상인 조건
        List<BooleanExpression> valueConditions = new ArrayList<>();

        if (values != null && !values.isEmpty()) {
            for (int i = 0; i < values.size(); i++) {
                if (values.get(i) >= 1) {
                    switch (i+1) {
                        case 1:
                            valueConditions.add(routine.isValue1.isTrue());
                            break;
                        case 2:
                            valueConditions.add(routine.isValue2.isTrue());
                            break;
                        case 3:
                            valueConditions.add(routine.isValue3.isTrue());
                            break;
                        case 4:
                            valueConditions.add(routine.isValue4.isTrue());
                            break;
                        case 5:
                            valueConditions.add(routine.isValue5.isTrue());
                            break;
                        case 6:
                            valueConditions.add(routine.isValue6.isTrue());
                            break;
                    }
                }
            }
        }


        // (OR) is_default가 true인 조건
        BooleanExpression finalCondition = routine.isDefault.isTrue();

        if (!valueConditions.isEmpty()) {
            BooleanExpression[] valueConditionsArray = new BooleanExpression[valueConditions.size()];
            valueConditions.toArray(valueConditionsArray);

            finalCondition = finalCondition.or(Expressions.anyOf(valueConditionsArray));
        }


        // (AND) memberRoutines와 중복되지 않는 조건. 서브쿼리 사용 없이 기존 메모리의 정보를 활용.
        List<Long> memberRoutineIds = memberRoutines.stream()
                .map(memberRoutine -> memberRoutine.getRoutine().getId())
                .collect(Collectors.toList());

        finalCondition = finalCondition.and(routine.id.notIn(memberRoutineIds));

        List<RoutineInfo> results = jpaQueryFactory
                .select(Projections.constructor(RoutineInfo.class, routine.id, routine.routineName)) // RoutineInfo로 변환하기 위함
                .from(routine)
                .where(finalCondition)
                .fetch();

        return results;
    }

}
