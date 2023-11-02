package com.ssafy.hanol.notification.service.dto.request;

import com.google.firebase.messaging.MulticastMessage;
import lombok.Builder;
import lombok.Getter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
public class FcmMulticastMessageRequest extends BaseFcmMessageRequest {

    private List<String> tokens;

    @Builder
    public FcmMulticastMessageRequest(Notification notification, Data data, List<String> tokens) {
        super(notification, data);
        this.tokens = tokens;
    }

    public MulticastMessage toMulticastMessage() {
        return MulticastMessage.builder()
                .addAllTokens(this.tokens)
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
