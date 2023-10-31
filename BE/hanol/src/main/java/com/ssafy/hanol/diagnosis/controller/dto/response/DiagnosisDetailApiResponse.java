package com.ssafy.hanol.diagnosis.controller.dto.response;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.ssafy.hanol.diagnosis.service.DiagnosisInfo;
import com.ssafy.hanol.diagnosis.service.dto.response.DiagnosisDetailResponse;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class DiagnosisDetailApiResponse {

    private DiagnosisInfo diagnosisInfo;

    public DiagnosisDetailApiResponse(DiagnosisInfo diagnosisInfo) {
        this.diagnosisInfo = diagnosisInfo;
    }

    public static DiagnosisDetailApiResponse from(DiagnosisDetailResponse diagnosisDetailResponse) {
        return new DiagnosisDetailApiResponse(diagnosisDetailResponse.getDiagnosisInfo());
    }
}
