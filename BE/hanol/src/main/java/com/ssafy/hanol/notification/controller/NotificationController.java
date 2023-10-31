package com.ssafy.hanol.notification.controller;

import com.ssafy.hanol.common.response.ResponseFactory;
import com.ssafy.hanol.notification.controller.dto.request.NotificationModifyApiRequest;
import com.ssafy.hanol.notification.controller.dto.response.NotificationApiResponse;
import com.ssafy.hanol.notification.service.NotificationService;
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

    @GetMapping
    public ResponseEntity<?> notificationList() {
        NotificationResponse result = notificationService.findNotifications();
        return ResponseFactory.success("알림 설정 조회 완료", NotificationApiResponse.from(result));
    }

    @PatchMapping
    public ResponseEntity<?> notificationModify(@Validated @RequestBody NotificationModifyApiRequest notificationModifyApiRequest) {
        NotificationResponse result = notificationService.modifyNotification(notificationModifyApiRequest.toApplicationDto());
        return ResponseFactory.success("알림 설정 변경 완료", NotificationApiResponse.from(result));
    }

}
