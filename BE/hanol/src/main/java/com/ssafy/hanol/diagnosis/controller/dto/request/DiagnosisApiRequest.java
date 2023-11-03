package com.ssafy.hanol.diagnosis.controller.dto.request;


import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.ssafy.hanol.diagnosis.service.dto.request.DiagnosisRequest;
import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class DiagnosisApiRequest {

    @NotNull(message = "deviceType cannot be null")
    private int deviceType;

    @NotNull(message = "scanPart cannot be null")
    private int scanPart;

    public DiagnosisRequest toDiagnosisRequest(Long memberId) {
        return DiagnosisRequest.builder()
                .deviceType(this.deviceType)
                .scanPart(this.scanPart)
                .memberId(memberId)
                .build();
    }
}
