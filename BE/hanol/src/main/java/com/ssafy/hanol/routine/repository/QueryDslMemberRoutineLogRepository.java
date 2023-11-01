package com.ssafy.hanol.routine.repository;

import com.querydsl.core.Tuple;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.hanol.routine.domain.QMemberRoutine;
import com.ssafy.hanol.routine.domain.QMemberRoutineLog;
import com.ssafy.hanol.routine.domain.QRoutine;
import com.ssafy.hanol.routine.service.RoutineLogInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Repository
@Slf4j
@RequiredArgsConstructor
public class QueryDslMemberRoutineLogRepository {

    private final JPAQueryFactory jpaQueryFactory;
    private final EntityManager entityManager;


    public void deleteRoutinesForTodayByRoutineId(Long memberId, List<Long> removedRoutines, LocalDate today) {

        QMemberRoutineLog memberRoutineLog = QMemberRoutineLog.memberRoutineLog;

        BooleanExpression criteria = memberRoutineLog.member.id.eq(memberId)
                .and(memberRoutineLog.date.eq(today))
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
        QRoutine routine = QRoutine.routine;

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
                            routine.routineName,
                            memberRoutineLog.date,
                            memberRoutineLog.isDone,
                            memberRoutine.id,
                            memberRoutine.isNotificationActive,
                            memberRoutine.notificationTime))
                    .from(memberRoutineLog)
                    .join(memberRoutineLog.routine, routine)
                    .join(memberRoutine).on(memberRoutineLog.routine.id.eq(memberRoutine.routine.id))
                    .where(criteria)
                    .fetch();
        } else {
            results = jpaQueryFactory
                    .select(Projections.constructor(RoutineLogInfo.class,
                            memberRoutineLog.id,
                            memberRoutineLog.routine.id,
                            routine.routineName,
                            memberRoutineLog.date,
                            memberRoutineLog.isDone
                    ))
                    .from(memberRoutineLog)
                    .join(memberRoutineLog.routine, routine)
                    .where(criteria)
                    .fetch();
        }

        return results;
    }


    public Map<LocalDate, Double> computeAchievementRates(Long memberId, LocalDate startDate, LocalDate endDate) {

        QMemberRoutineLog memberRoutineLog = QMemberRoutineLog.memberRoutineLog;

        Map<LocalDate, Double> achievementRates = new LinkedHashMap<>();

        // 달성한 루틴 개수 조회
        List<Tuple> doneRoutinesCounts = jpaQueryFactory
                .select(memberRoutineLog.date, memberRoutineLog.isDone.count())
                .from(memberRoutineLog)
                .where(memberRoutineLog.member.id.eq(memberId)
                        .and(memberRoutineLog.date.between(startDate, endDate))
                        .and(memberRoutineLog.isDone.isTrue()))
                .groupBy(memberRoutineLog.date)
                .fetch();

        // 전체 루틴 개수 조회
        List<Tuple> totalRoutinesCounts = jpaQueryFactory
                .select(memberRoutineLog.date, memberRoutineLog.id.count())
                .from(memberRoutineLog)
                .where(memberRoutineLog.member.id.eq(memberId)
                        .and(memberRoutineLog.date.between(startDate, endDate)))
                .groupBy(memberRoutineLog.date)
                .fetch();


        // 일자별 달성률 계산
        // - Map으로 변환
        Map<LocalDate, Long> totalRoutinesMap = totalRoutinesCounts.stream()
                .collect(Collectors.toMap(
                        tuple -> tuple.get(memberRoutineLog.date),
                        tuple -> tuple.get(memberRoutineLog.id.count())
                ));

        Map<LocalDate, Long> doneRoutinesMap = doneRoutinesCounts.stream()
                .collect(Collectors.toMap(
                        tuple -> tuple.get(memberRoutineLog.date),
                        tuple -> tuple.get(memberRoutineLog.isDone.count())
                ));

        // - 달성률 계산
        LocalDate currentDate = startDate;
        while (!currentDate.isAfter(endDate)) {
            Long completedCount = doneRoutinesMap.getOrDefault(currentDate, 0L);
            Long totalCount = totalRoutinesMap.getOrDefault(currentDate, 0L);

            double rate = (totalCount == 0) ? 0 : ((double) completedCount / totalCount) * 100;

            achievementRates.put(currentDate, rate);

            currentDate = currentDate.plusDays(1);
        }

        return achievementRates;
    }
}
