package com.ssafy.hanol.diagnosis.service;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.ssafy.hanol.diagnosis.domain.Diagnosis;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class DiagnosisInfo {

    private Long diagnosisId;
    private Long memberId;
    private int value1;
    private int value2;
    private int value3;
    private int value4;
    private int value5;
    private int value6;
    private String imageUrl;
    private int deviceType;
    private int scanPart;
    @JsonFormat(pattern = "yyyy-MM-dd' 'HH:mm:ss")
    private LocalDateTime createdDate;

    // QueryDSL 추출용 생성자
    public DiagnosisInfo(Long diagnosisId, Long memberId, int value1, int value2, int value3, int value4, int value5, int value6, String imageUrl, int deviceType, int scanPart, LocalDateTime createdDate) {
        this.diagnosisId = diagnosisId;
        this.memberId = memberId;
        this.value1 = value1;
        this.value2 = value2;
        this.value3 = value3;
        this.value4 = value4;
        this.value5 = value5;
        this.value6 = value6;
        this.imageUrl = imageUrl;
        this.deviceType = deviceType;
        this.scanPart = scanPart;
        this.createdDate = createdDate;
    }

    public static DiagnosisInfo from(Diagnosis diagnosis) {
        return new DiagnosisInfo(diagnosis.getId(), diagnosis.getMember().getId(),
                diagnosis.getValue1(), diagnosis.getValue2(), diagnosis.getValue3(),
                diagnosis.getValue4(), diagnosis.getValue5(), diagnosis.getValue6(),
                diagnosis.getImageUrl(), diagnosis.getDeviceType(), diagnosis.getScanPart(), diagnosis.getCreatedDate());
    }
}