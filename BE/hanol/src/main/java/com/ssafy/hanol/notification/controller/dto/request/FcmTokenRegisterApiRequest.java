package com.ssafy.hanol.notification.controller.dto.request;

import com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.ssafy.hanol.notification.service.dto.request.FcmTokenRequest;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@JsonNaming(SnakeCaseStrategy.class)
public class FcmTokenRegisterApiRequest {

    @NotNull(message = "fcmToken cannot be null")
    private String fcmToken;

    @Builder
    public FcmTokenRegisterApiRequest(String fcmToken) {
        this.fcmToken = fcmToken;
    }

    public FcmTokenRequest toFcmTokenRequest(String userAgent, Long memberId) {
        return FcmTokenRequest.builder()
                .fcmToken(fcmToken)
                .deviceInfo(userAgent)
                .memberId(memberId)
                .build();
    }
}
