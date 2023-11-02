package com.ssafy.hanol.notification.service;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class FcmMessage {

    private boolean validateOnly;
    private Message message;

    @Getter
    @Builder
    @AllArgsConstructor
    public static class Message {
        private String token;
        private Notification notification;  // 앱이 백그라운드 상태일 때 표시되는 메시지
        private Data data;  // 앱이 포그라운드에서 실행될 때 클라이언트가 처리할 메시지
    }

    @Getter
    @Builder
    @AllArgsConstructor
    public static class Notification {
        private String title;
        private String body;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    public static class Data {
        private String title;
        private String body;
        private String clickAction;
    }

}
