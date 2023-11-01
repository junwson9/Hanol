package com.ssafy.hanol.notification.controller.dto.request;

import com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.ssafy.hanol.notification.service.dto.request.FCMTokenRequest;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@JsonNaming(SnakeCaseStrategy.class)
public class FCMTokenRegisterApiRequest {

    @NotNull(message = "fcmToken cannot be null")
    private String fcmToken;

    @Builder
    public FCMTokenRegisterApiRequest(String fcmToken) {
        this.fcmToken = fcmToken;
    }

    public FCMTokenRequest toFCMTokenRequest(String userAgent, Long memberId) {
        return FCMTokenRequest.builder()
                .fcmToken(fcmToken)
                .deviceInfo(userAgent)
                .memberId(memberId)
                .build();
    }
}
