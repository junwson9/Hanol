package com.ssafy.hanol.notification.controller.dto.request;

import com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.ssafy.hanol.notification.domain.NotificationType;
import com.ssafy.hanol.notification.service.dto.request.NotificationModifyRequest;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@JsonNaming(SnakeCaseStrategy.class)
public class NotificationModifyApiRequest {

    @NotNull(message = "Notification cannot be null")
    private NotificationType notificationType;

    @NotNull(message = "isActive cannot be null")
    private Boolean isActive;

    public NotificationModifyRequest toApplicationDto() {
        return NotificationModifyRequest.builder()
                .notificationType(notificationType)
                .isActive(isActive)
                .build();
    }


}
