package com.ssafy.hanol.notification.controller;

import com.ssafy.hanol.common.response.ResponseFactory;
import com.ssafy.hanol.notification.service.FCMService;
import com.ssafy.hanol.global.config.auth.AuthMember;
import com.ssafy.hanol.global.config.auth.AuthenticatedMember;
import com.ssafy.hanol.notification.controller.dto.request.FCMTokenRegisterApiRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/api/notifications")
@RequiredArgsConstructor
public class FCMController {

    private final FCMService fcmService;

    @PostMapping("/token")
    public ResponseEntity<?> fcmTokenAdd(@RequestHeader("User-Agent") String userAgent,
                                         @RequestBody FCMTokenRegisterApiRequest fcmTokenRegisterApiRequest,
                                         @AuthenticatedMember AuthMember authMember) {
        log.info("fcm token 등록 요청. AuthMemberId: {}, User-Agent: {}", authMember.getId(), userAgent);
        fcmService.addToken(fcmTokenRegisterApiRequest.toFCMTokenRequest(userAgent, authMember.getId()));
        return ResponseFactory.success("FCM token 등록 성공");
    }

}
