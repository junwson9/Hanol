package com.ssafy.hanol.diagnosis.service;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.ssafy.hanol.diagnosis.domain.Diagnosis;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class DiagnosisInfo {

    private Long diagnosisId;
    private Long memberId;
    private List<Integer> values;
    private String imageUrl;
    private int deviceType;
    private int scanPart;
    private LocalDateTime createDate;

    public DiagnosisInfo(Long diagnosisId, Long memberId, List<Integer> values, String imageUrl, int deviceType, int scanPart, LocalDateTime createDate) {
        this.diagnosisId = diagnosisId;
        this.memberId = memberId;
        this.values = values;
        this.imageUrl = imageUrl;
        this.deviceType = deviceType;
        this.scanPart = scanPart;
        this.createDate = createDate;
    }

    public static DiagnosisInfo from(Diagnosis diagnosis) {
        return new DiagnosisInfo(diagnosis.getId(), diagnosis.getMember().getId(), diagnosis.getValuesAsList(), diagnosis.getImageUrl(), diagnosis.getDeviceType(), diagnosis.getScanPart(), diagnosis.getCreateDate());
    }
}