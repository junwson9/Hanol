package com.ssafy.hanol.notification.service;

import com.ssafy.hanol.notification.repository.FcmRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class NotificationSchedulerService {

    private final FcmRepository fcmRepository;


    /* 전체 루틴 알림 스케쥴
     - 매일 21시 수행
     - 회원루틴 이력 테이블에서 is_done = false 이고 created_date = 오늘인 회원 중
     - 알림 설정 테이블에서 is_check_routine_active = true 인 회원 중
     - token 테이블의 member_id 가 일치하는 회원 조회
     */
    public void sendCheckRoutinePush() {

        FcmMulticastMessageRequest request = FcmMulticastMessageRequest.builder()
                .notification(BaseFcmMessageRequest.Notification.builder()
                                .title("오늘의 루틴 체크 완료하셨나요?")
                                .body("하루 하루 건강한 실천으로 한올 한올 건강한 모발을 만들어요!")
                                .build())
                .data(BaseFcmMessageRequest.Data.builder()
                                .title("오늘의 루틴 체크 완료하셨나요?")
                                .body("하루 하루 건강한 실천으로 한올 한올 건강한 모발을 만들어요!")
                                .clickAction("/")  // TODO 이동할 페이지 정하기
                                .build())
                .build();

    }


    public void extractCheckRoutinePushTargets() {

        List<PushTargetInfo> pushTargets = fcmRepository.findCheckRoutinePushTargets();

    }
}
