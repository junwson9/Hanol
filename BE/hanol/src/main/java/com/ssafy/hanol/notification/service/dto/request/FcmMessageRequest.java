package com.ssafy.hanol.notification.service.dto.request;

import com.google.firebase.messaging.Message;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@Getter
@NoArgsConstructor
public class FcmMessageRequest extends BaseFcmMessageRequest {

    private String token;

    @Builder
    public FcmMessageRequest(Notification notification, Data data, String token) {
        super(notification, data);
        this.token = token;
    }

    public Message toMessage() {
        return Message.builder()
                .setToken(this.token)
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
