package com.ssafy.hanol.diagnosis.service.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class DiagnosisRequest {

    private Long memberId;
    private int deviceType;
    private int scanPart;

    @Builder
    public DiagnosisRequest(Long memberId, int deviceType, int scanPart) {
        this.memberId = memberId;
        this.deviceType = deviceType;
        this.scanPart = scanPart;
    }
}
