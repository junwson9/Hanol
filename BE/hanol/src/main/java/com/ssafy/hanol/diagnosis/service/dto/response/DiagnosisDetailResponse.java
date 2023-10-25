package com.ssafy.hanol.diagnosis.service.dto.response;

import com.ssafy.hanol.diagnosis.domain.Diagnosis;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class DiagnosisDetailResponse {

    private Long diagnosisId;
    private Long memberId;
    private List<Integer> values;
    private String imageUrl;
    private int deviceType;
    private int scanPart;
    private LocalDateTime createDate;

    public DiagnosisDetailResponse(Long id, Long memberId, List<Integer> values, String imageUrl, int deviceType, int scanPart, LocalDateTime createDate) {
        this.diagnosisId = id;
        this.memberId = memberId;
        this.values = values;
        this.imageUrl = imageUrl;
        this.deviceType = deviceType;
        this.scanPart = scanPart;
        this.createDate = createDate;
    }

    public static DiagnosisDetailResponse from(Diagnosis diagnosis) {
        return new DiagnosisDetailResponse(diagnosis.getId(), diagnosis.getMember().getId(), diagnosis.getValuesAsList(), diagnosis.getImageUrl(), diagnosis.getDeviceType(), diagnosis.getScanPart(), diagnosis.getCreateDate());
    }
}
