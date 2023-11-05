package com.ssafy.hanol.notification.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.hanol.member.domain.QMember;
import com.ssafy.hanol.notification.domain.QNotificationConfiguration;
import com.ssafy.hanol.notification.domain.QNotificationToken;
import com.ssafy.hanol.notification.service.fcm.EachRoutinePushTargetInfo;
import com.ssafy.hanol.notification.service.fcm.PushTargetInfo;
import com.ssafy.hanol.routine.domain.QMemberRoutine;
import com.ssafy.hanol.routine.domain.QMemberRoutineLog;
import com.ssafy.hanol.routine.domain.QRoutine;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Repository
@Slf4j
@RequiredArgsConstructor
public class QueryDslFcmRepository {

    private final JPAQueryFactory jpaQueryFactory;

    /**
     * 전체 루틴 확인 푸시 대상 추출
     * member_routineLog 테이블에서 is_done = false 이고 date = 오늘인 회원 중
     * notification_configuration 테이블에서 is_check_routine_active = true 인 회원 중
     * notification_token 테이블의 member_id 가 일치하는 회원의 token, member_id, member_nickname 조회
     *
     * @return List<PushTargetInfo>
     */
    public List<PushTargetInfo> selectCheckRoutinePushTargets() {

        QMemberRoutineLog memberRoutineLog = QMemberRoutineLog.memberRoutineLog;
        QNotificationConfiguration notificationConfiguration = QNotificationConfiguration.notificationConfiguration;
        QNotificationToken notificationToken = QNotificationToken.notificationToken;
        QMember member = QMember.member;

        LocalDate today = LocalDate.now();

        List<PushTargetInfo> pushTargets = jpaQueryFactory
                .select(Projections.constructor(PushTargetInfo.class,
                                memberRoutineLog.member.id,
                                member.name,
                                notificationToken.token))
                .from(memberRoutineLog)
                .join(member).on(memberRoutineLog.member.id.eq(member.id))
                .join(notificationToken).on(member.id.eq(notificationToken.member.id))
                .join(notificationConfiguration).on(member.id.eq(notificationConfiguration.member.id))
                .where(memberRoutineLog.date.eq(today),
                        memberRoutineLog.isDone.eq(false),
                        notificationConfiguration.isCheckRoutineActive.isTrue())
                .distinct()
                .fetch();

        return pushTargets;
    }


    /**
     * 개별 루틴 알림 푸시 대상 추출
     * notification_configuration 테이블에서 is_individual_routine_active = true 인 member_id 이면서
     * member_routine 테이블의 is_notification_active = true 이고 notification_time = time(LocalTime 형태의 파라미터)인 데이터의 member_id와 routine_id를 가져온다.
     * notification_token 테이블의 member_id 가 일치하는 회원의 token, member_id, member_nickname 조회한다.
     * routine 테이블을 조인해서 routine_id 로 routine_name을 가져와야 한다.
     *
     * @return List<PushTargetInfo>
     */

    public List<EachRoutinePushTargetInfo> selectEachRoutinePushTargets(LocalTime time) {

        QMember member = QMember.member;
        QRoutine routine = QRoutine.routine;
        QMemberRoutine memberRoutine = QMemberRoutine.memberRoutine;
        QNotificationConfiguration notificationConfiguration = QNotificationConfiguration.notificationConfiguration;
        QNotificationToken notificationToken = QNotificationToken.notificationToken;

        List<EachRoutinePushTargetInfo> result = jpaQueryFactory
                .select(Projections.constructor(EachRoutinePushTargetInfo.class,
                    notificationConfiguration.member.id,
                    member.name,
                    notificationToken.token,
                    memberRoutine.routine.id,
                    routine.routineName
                    ))
                .from(notificationConfiguration)
                .innerJoin(memberRoutine).on(notificationConfiguration.member.id.eq(memberRoutine.member.id))
                .innerJoin(notificationToken).on(notificationConfiguration.member.id.eq(notificationToken.member.id))
                .innerJoin(member).on(notificationConfiguration.member.id.eq(member.id))
                .innerJoin(routine).on(memberRoutine.routine.id.eq(routine.id))
                .where(notificationConfiguration.isIndividualRoutineActive.isTrue()
                        .and(memberRoutine.isNotificationActive.isTrue())
                        .and(memberRoutine.notificationTime.eq(time)))
                .fetch();

        return result;
    }
}
