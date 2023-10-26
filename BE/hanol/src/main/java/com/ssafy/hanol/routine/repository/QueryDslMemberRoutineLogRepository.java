package com.ssafy.hanol.routine.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.hanol.routine.domain.QMemberRoutine;
import com.ssafy.hanol.routine.domain.QMemberRoutineLog;
import com.ssafy.hanol.routine.service.RoutineLogInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.util.ArrayList;
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

    public List<RoutineLogInfo> selectRoutineLogsByMemberIdAndDate(Long memberId, LocalDate date) {

        QMemberRoutineLog memberRoutineLog = QMemberRoutineLog.memberRoutineLog;
        QMemberRoutine memberRoutine = QMemberRoutine.memberRoutine;

        BooleanExpression criteria = memberRoutineLog.member.id.eq(memberId)
                .and(memberRoutineLog.date.eq(date));

        boolean isToday = LocalDate.now().isEqual(date);

        List<RoutineLogInfo> results = new ArrayList<>();

        // 오늘인 경우 알림 시간을 반환하고, 오늘이 아닌 경우 알림 시간을 반환하지 않음
        if(isToday) {
            results = jpaQueryFactory
                    .select(Projections.constructor(RoutineLogInfo.class,
                            memberRoutineLog.id,
                            memberRoutineLog.routine.id,
                            memberRoutineLog.routine.routineName,
                            memberRoutineLog.date,
                            memberRoutineLog.isDone,
                            memberRoutine.isNotificationActive,
                            memberRoutine.notificationTime))
                    .from(memberRoutineLog)
                    .join(memberRoutine).on(memberRoutineLog.routine.id.eq(memberRoutine.routine.id))
                    .where(criteria)
                    .fetch();
        } else {
            results = jpaQueryFactory
                    .select(Projections.constructor(RoutineLogInfo.class,
                            memberRoutineLog.id,
                            memberRoutineLog.routine.id,
                            memberRoutineLog.routine.routineName,
                            memberRoutineLog.date,
                            memberRoutineLog.isDone
                    ))
                    .from(memberRoutineLog)
                    .where(criteria)
                    .fetch();
        }

        return results;
    }
}
