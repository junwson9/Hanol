package com.ssafy.hanol.notification.controller;

import com.ssafy.hanol.common.response.ResponseFactory;
import com.ssafy.hanol.global.config.auth.AuthMember;
import com.ssafy.hanol.global.config.auth.AuthenticatedMember;
import com.ssafy.hanol.notification.controller.dto.request.FcmTokenRegisterApiRequest;
import com.ssafy.hanol.notification.controller.dto.request.NotificationModifyApiRequest;
import com.ssafy.hanol.notification.controller.dto.response.NotificationApiResponse;
import com.ssafy.hanol.notification.service.NotificationSchedulerService;
import com.ssafy.hanol.notification.service.NotificationService;
import com.ssafy.hanol.notification.service.NotificationTokenService;
import com.ssafy.hanol.notification.service.dto.response.NotificationResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/api/notifications")
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationService notificationService;
    private final NotificationTokenService notificationTokenService;
    private final NotificationSchedulerService notificationSchedulerService;

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
                                         @RequestBody FcmTokenRegisterApiRequest fcmTokenRegisterApiRequest,
                                         @AuthenticatedMember AuthMember authMember) {
        log.info("fcm token 등록 요청. AuthMemberId: {}, User-Agent: {}", authMember.getId(), userAgent);
        notificationTokenService.addToken(fcmTokenRegisterApiRequest.toFcmTokenRequest(userAgent, authMember.getId()));
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

}
