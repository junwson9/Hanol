package com.ssafy.hanol.notification.service;

import com.google.firebase.messaging.Message;
import com.ssafy.hanol.notification.repository.FcmRepository;
import com.ssafy.hanol.notification.service.dto.request.BaseFcmMessageRequest;
import com.ssafy.hanol.notification.service.dto.request.FcmMessageRequest;
import com.ssafy.hanol.notification.service.fcm.EachRoutinePushTargetInfo;
import com.ssafy.hanol.notification.service.fcm.PushTargetInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class NotificationSchedulerService {

    private final FcmRepository fcmRepository;
    private final FcmService fcmService;


    /**
     * 루틴 전체 확인 알림 스케쥴
     *
     * 매일 21시 수행
     * 오늘 수행하지 않은 루틴 이력이 있는 회원 중 알림 활성화한 회원에게 푸시 발송
     */
    public void sendCheckRoutinePush() {
        List<PushTargetInfo> pushTargets = fcmRepository.selectCheckRoutinePushTargets();
        if (!isValidPushTargets(pushTargets, "No push targets found for CheckRoutine.")) {
            return;
        }

        List<Message> messages = new ArrayList<>();
        for (PushTargetInfo pushTarget : pushTargets) {
            String title = pushTarget.getMemberName() + "님 오늘의 루틴 체크 완료하셨나요?";
            String body = "하루 하루 건강한 실천으로 한올 한올 건강한 모발을 만들어요!";
            String clickAction = "/"; // TODO 이동할 페이지 지정
            Message message = createPushMessage(pushTarget.getToken(), title, body, clickAction);
            messages.add(message);
        }

        fcmService.sendBatchMessage(messages, false);
    }


    /**
     * 개별 루틴 알림
     *
     * 매일 9시, 13시, 17시 수행
     * 개별 루틴 알림 활성화한 회원이 해당 시간에 알림 설정을 해둔 루틴이 있으면 푸시 발송
     */
    public void sendEachRoutinePush() {
        LocalTime now = LocalTime.now();
        LocalTime time = now.withMinute(0).withSecond(0);

        List<EachRoutinePushTargetInfo> pushTargets = fcmRepository.selectEachRoutinePushTargets(time);
        if (!isValidPushTargets(pushTargets, "No push targets found for EachRoutine at time: " + time)) {
            return;
        }

        List<Message> messages = new ArrayList<>();
        for (EachRoutinePushTargetInfo pushTarget : pushTargets) {
            String title = pushTarget.getMemberName() + "님 " + pushTarget.getRoutineName() + "하실 시간이에요!";
            String body = "건강한 두피를 위해 지금 실천하고 기록을 남겨주세요!";
            String clickAction = "/"; // TODO 이동할 페이지 지정
            Message message = createPushMessage(pushTarget.getToken(), title, body, clickAction);
            messages.add(message);
        }

        fcmService.sendBatchMessage(messages, false);
    }


    // 푸시 메시지를 만드는 로직
    private Message createPushMessage(String token, String title, String body, String clickAction) {
        return FcmMessageRequest.builder()
                .notification(BaseFcmMessageRequest.Notification.builder()
                        .title(title)
                        .body(body)
                        .build())
                .data(BaseFcmMessageRequest.Data.builder()
                        .title(title)
                        .body(body)
                        .clickAction(clickAction)
                        .build())
                .token(token)
                .build()
                .toMessage();
    }


    // 푸시 대상이 없는 경우 로깅
    private boolean isValidPushTargets(List<?> pushTargets, String logMessage) {
        if (pushTargets == null || pushTargets.isEmpty()) {
            log.info(logMessage);
            return false;
        }
        return true;
    }


    // 테스트용 임시 코드
    public List<PushTargetInfo> extractCheckRoutinePushTargets() {
        List<PushTargetInfo> pushTargets = fcmRepository.selectCheckRoutinePushTargets();
        log.info("pushTargets: {}", pushTargets);
        return pushTargets;
    }

    // 테스트용 임시 코드
    public List<EachRoutinePushTargetInfo> extractEachRoutinePushTargets() {
        LocalTime time = LocalTime.of(13, 00, 00); // 임시데이터
        List<EachRoutinePushTargetInfo> pushTargets = fcmRepository.selectEachRoutinePushTargets(time);
        log.info("pushTargets: {}", pushTargets);
        return pushTargets;
    }

}
