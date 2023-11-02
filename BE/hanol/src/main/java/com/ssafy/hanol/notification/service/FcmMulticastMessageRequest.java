package com.ssafy.hanol.notification.service;

import com.google.firebase.messaging.MulticastMessage;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
public class FcmMulticastMessageRequest extends BaseFcmMessage{

    private List<String> tokens;

    public FcmMulticastMessageRequest(Notification notification, Data data) {
        super(notification, data);
    }

    public MulticastMessage toMulticastMessage() {
        return MulticastMessage.builder()
                .addAllTokens(this.tokens)
                .setNotification(convertToFirebaseNotification(this.notification))
                .putAllData(convertToDataMap(this.data))
                .build();
    }

    private com.google.firebase.messaging.Notification convertToFirebaseNotification(BaseFcmMessage.Notification notification) {
        return com.google.firebase.messaging.Notification.builder()
                .setTitle(notification.getTitle())
                .setBody(notification.getBody())
                .build();
    }

    private Map<String, String> convertToDataMap(BaseFcmMessage.Data data) {
        Map<String, String> dataMap = new HashMap<>();
        dataMap.put("title", data.getTitle());
        dataMap.put("body", data.getBody());
        dataMap.put("click_action", data.getClickAction());
        return dataMap;
    }

}
