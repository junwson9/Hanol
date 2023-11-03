package com.ssafy.hanol.diagnosis.service;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class DiagnosisIdInfo {

    private Long diagnosisId;
    @JsonFormat(pattern = "yyyy-MM-dd' 'HH:mm:ss")
    private LocalDateTime createDate;

    public DiagnosisIdInfo(Long diagnosisId, LocalDateTime createDate) {
        this.diagnosisId = diagnosisId;
        this.createDate = createDate;
    }
}
