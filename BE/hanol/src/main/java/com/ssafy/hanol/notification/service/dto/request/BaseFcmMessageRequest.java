package com.ssafy.hanol.notification.service.dto.request;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;

@Getter
@AllArgsConstructor
@ToString
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public abstract class BaseFcmMessageRequest {

    protected Notification notification;  // 앱이 백그라운드 상태일 때 표시되는 메시지
    protected Data data;  // 앱이 포그라운드에서 실행될 때 클라이언트가 처리할 메시지

    public BaseFcmMessageRequest() {
    }

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Notification {
        private String title;
        private String body;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Data {
        private String title;
        private String body;
        private String clickAction;
    }
}
