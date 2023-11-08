package com.ssafy.hanol.routine.controller.dto.request;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.ssafy.hanol.routine.service.dto.request.RoutineNotificationModifyRequest;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalTime;

@Getter
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class RoutineNotificationModifyApiRequest {

    @NotNull
    private Boolean isNotificationActive;

    @Pattern(regexp = "^([01]?[0-9]|2[0-3]):[0-5][0-9]:[0-5][0-9]$", message = "Invalid time format")
    private String notificationTime;

    public RoutineNotificationModifyRequest toApplicationDto() {
        return RoutineNotificationModifyRequest.builder()
                .isNotificationActive(isNotificationActive)
                .notificationTime(LocalTime.parse(notificationTime))
                .build();
    }

}
