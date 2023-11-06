package com.ssafy.hanol.diagnosis.service.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Getter
@NoArgsConstructor
public class DiagnosisRequest {

    private Long memberId;
    private int deviceType;
    private int scanPart;
    private MultipartFile file;

    @Builder

    public DiagnosisRequest(Long memberId, int deviceType, int scanPart, MultipartFile file) {
        this.memberId = memberId;
        this.deviceType = deviceType;
        this.scanPart = scanPart;
        this.file = file;
    }
}
