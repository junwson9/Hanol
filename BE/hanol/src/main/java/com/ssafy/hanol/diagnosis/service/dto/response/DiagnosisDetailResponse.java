package com.ssafy.hanol.diagnosis.service.dto.response;

import com.ssafy.hanol.diagnosis.service.DiagnosisInfo;
import lombok.Getter;

@Getter
public class DiagnosisDetailResponse {

    private DiagnosisInfo diagnosisInfo;

    public DiagnosisDetailResponse(DiagnosisInfo diagnosisInfo) {
        this.diagnosisInfo = diagnosisInfo;
    }

    public static DiagnosisDetailResponse from(DiagnosisInfo info) {
        return new DiagnosisDetailResponse(info);
    }
}
