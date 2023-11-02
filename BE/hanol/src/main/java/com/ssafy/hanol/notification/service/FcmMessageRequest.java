package com.ssafy.hanol.notification.service;

import com.google.firebase.messaging.MulticastMessage;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
public class FcmMessageRequest extends BaseFcmMessageRequest {

    private String token;

    public FcmMessageRequest(Notification notification, Data data) {
        super(notification, data);
    }

    public MulticastMessage toMulticastMessage() {
        return MulticastMessage.builder()
                .addToken(this.token)
                .setNotification(toNotification(this.notification))
                .putAllData(toDataMap(this.data))
                .build();
    }

    private com.google.firebase.messaging.Notification toNotification(BaseFcmMessageRequest.Notification notification) {
        return com.google.firebase.messaging.Notification.builder()
                .setTitle(notification.getTitle())
                .setBody(notification.getBody())
                .build();
    }

    private Map<String, String> toDataMap(BaseFcmMessageRequest.Data data) {
        Map<String, String> dataMap = new HashMap<>();
        dataMap.put("title", data.getTitle());
        dataMap.put("body", data.getBody());
        dataMap.put("click_action", data.getClickAction());
        return dataMap;
    }
}
