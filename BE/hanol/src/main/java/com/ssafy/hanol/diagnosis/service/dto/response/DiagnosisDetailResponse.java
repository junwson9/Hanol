package com.ssafy.hanol.diagnosis.service.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.ssafy.hanol.diagnosis.service.DiagnosisInfo;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class DiagnosisDetailResponse {

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

    public DiagnosisDetailResponse(Long diagnosisId, Long memberId, int value1, int value2, int value3, int value4, int value5, int value6, String imageUrl, int deviceType, int scanPart, LocalDateTime createdDate) {
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

    public static DiagnosisDetailResponse from(DiagnosisInfo info) {
        return new DiagnosisDetailResponse(
                info.getDiagnosisId(),
                info.getMemberId(),
                info.getValue1(), info.getValue2(), info.getValue3(),
                info.getValue4(), info.getValue5(), info.getValue6(),
                info.getImageUrl(),
                info.getDeviceType(),
                info.getScanPart(),
                info.getCreatedDate());
    }
}
