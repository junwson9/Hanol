package com.ssafy.hanol.notification.controller;

import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import com.ssafy.hanol.common.response.ResponseFactory;
import com.ssafy.hanol.global.config.auth.AuthMember;
import com.ssafy.hanol.global.config.auth.AuthenticatedMember;
import com.ssafy.hanol.notification.controller.dto.request.FcmTokenApiRequest;
import com.ssafy.hanol.notification.controller.dto.request.NotificationModifyApiRequest;
import com.ssafy.hanol.notification.controller.dto.response.NotificationApiResponse;
import com.ssafy.hanol.notification.service.FcmService;
import com.ssafy.hanol.notification.service.NotificationSchedulerService;
import com.ssafy.hanol.notification.service.NotificationService;
import com.ssafy.hanol.notification.service.NotificationTokenService;
import com.ssafy.hanol.notification.service.dto.request.FcmMessageRequest;
import com.ssafy.hanol.notification.service.dto.response.NotificationResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/api/notifications")
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationService notificationService;
    private final NotificationTokenService notificationTokenService;
    private final NotificationSchedulerService notificationSchedulerService;
    private final FcmService fcmService;

    @GetMapping
    public ResponseEntity<?> notificationList(@AuthenticatedMember AuthMember authMember) {
        NotificationResponse result = notificationService.findNotifications(authMember.getId());
        return ResponseFactory.success("알림 설정 조회 완료", NotificationApiResponse.from(result));
    }

    @PatchMapping
    public ResponseEntity<?> notificationModify(@Validated @RequestBody NotificationModifyApiRequest notificationModifyApiRequest,
                                                @AuthenticatedMember AuthMember authMember) {
        NotificationResponse result = notificationService.modifyNotification(notificationModifyApiRequest.toApplicationDto(), authMember.getId());
        return ResponseFactory.success("알림 설정 변경 완료", NotificationApiResponse.from(result));
    }


    @PostMapping("/token")
    public ResponseEntity<?> fcmTokenAdd(@RequestHeader("User-Agent") String userAgent,
                                         @RequestBody FcmTokenApiRequest fcmTokenApiRequest,
                                         @AuthenticatedMember AuthMember authMember) {
        log.info("fcm token 등록 요청. AuthMemberId: {}, User-Agent: {}", authMember.getId(), userAgent);
        notificationTokenService.addToken(fcmTokenApiRequest.toFcmTokenRequest(userAgent, authMember.getId()));
        return ResponseFactory.success("FCM token 등록 완료");
    }



    // 푸시 발송 대상 추출 테스트 코드
    @GetMapping("/test")
    public ResponseEntity<?> getPushTarget() {
        notificationSchedulerService.extractCheckRoutinePushTargets();
        return ResponseFactory.success("추출완료");
    }

    @GetMapping("/test2")
    public ResponseEntity<?> getPushTarget2() {
        notificationSchedulerService.extractEachRoutinePushTargets();
        return ResponseFactory.success("추출완료");
    }

    // 푸시 발송 테스트 코드
    @PostMapping("/test3")
    public ResponseEntity<?> sendFcmTest1(@RequestBody FcmMessageRequest request) {
        List<Message> messages = new ArrayList<>();
        Message message = Message.builder()
                .setToken(request.getToken())
                .setNotification(Notification.builder()
                        .setTitle(request.getNotification().getTitle())
                        .setBody(request.getNotification().getBody())
                        .build())
                .putData("title", request.getData().getTitle())
                .putData("body", request.getData().getBody())
                .putData("click_action", request.getData().getClickAction())
                .build();
        messages.add(message);
        fcmService.sendBatchMessage(messages, true);
        return ResponseFactory.success("푸시 발송 테스트 완료");
    }

}
