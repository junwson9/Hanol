package com.ssafy.hanol.notification.service.dto.request;

import com.ssafy.hanol.notification.domain.NotificationType;
import lombok.Builder;
import lombok.Getter;

@Getter
public class NotificationModifyRequest {

    private NotificationType notificationType;
    private Boolean isActive;

    @Builder
    public NotificationModifyRequest(NotificationType notificationType, Boolean isActive) {
        this.notificationType = notificationType;
        this.isActive = isActive;
    }
}
