package com.ssafy.hanol.diagnosis.service.dto.response;

import com.ssafy.hanol.diagnosis.service.DiagnosisInfo;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class DiagnosisListResponse {

    private List<DiagnosisInfo> diagnosisInfoList;

    @Builder
    public DiagnosisListResponse(List<DiagnosisInfo> diagnosisInfoList) {
        this.diagnosisInfoList = diagnosisInfoList;
    }
}
