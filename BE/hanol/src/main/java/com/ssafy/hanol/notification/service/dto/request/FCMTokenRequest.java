package com.ssafy.hanol.notification.service.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class FCMTokenRequest {

    private Long memberId;
    private String deviceInfo;
    private String fcmToken;

    @Builder
    public FCMTokenRequest(Long memberId, String deviceInfo, String fcmToken) {
        this.memberId = memberId;
        this.deviceInfo = deviceInfo;
        this.fcmToken = fcmToken;
    }
}
