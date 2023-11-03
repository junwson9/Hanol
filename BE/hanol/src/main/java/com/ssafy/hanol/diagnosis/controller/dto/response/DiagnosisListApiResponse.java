package com.ssafy.hanol.diagnosis.controller.dto.response;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.ssafy.hanol.diagnosis.service.DiagnosisInfo;
import com.ssafy.hanol.diagnosis.service.dto.response.DiagnosisListResponse;
import lombok.Getter;

import java.util.List;

@Getter
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class DiagnosisListApiResponse {

    private List<DiagnosisInfo> diagnosisInfoList;

    public DiagnosisListApiResponse(List<DiagnosisInfo> diagnosisInfoList) {
        this.diagnosisInfoList = diagnosisInfoList;
    }

    public static DiagnosisListApiResponse from(DiagnosisListResponse diagnosisListResponse) {
        return new DiagnosisListApiResponse(diagnosisListResponse.getDiagnosisInfoList());
    }
}
