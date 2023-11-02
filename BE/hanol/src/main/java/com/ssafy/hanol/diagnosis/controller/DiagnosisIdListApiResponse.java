package com.ssafy.hanol.diagnosis.controller;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.ssafy.hanol.diagnosis.service.DiagnosisIdInfo;
import lombok.Getter;

import java.util.List;

@Getter
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class DiagnosisIdListApiResponse {

    private List<DiagnosisIdInfo> diagnosisIdList;

    public DiagnosisIdListApiResponse(List<DiagnosisIdInfo> diagnosisIdList) {
        this.diagnosisIdList = diagnosisIdList;
    }

    public static DiagnosisIdListApiResponse from(List<DiagnosisIdInfo> diagnosisIdList) {
        return new DiagnosisIdListApiResponse(diagnosisIdList);
    }
}
