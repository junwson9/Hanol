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
public class FcmTokenApiRequest {

//    @NotNull(message = "fcmToken cannot be null")  // TODO 주석 처리 해제 (테스트를 위해 임시 주석 처리)
    private String fcmToken;

    @Builder
    public FcmTokenApiRequest(String fcmToken) {
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
