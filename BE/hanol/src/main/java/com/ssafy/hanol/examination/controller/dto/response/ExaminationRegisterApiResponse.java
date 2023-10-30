package com.ssafy.hanol.examination.controller.dto.response;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.ssafy.hanol.examination.service.dto.response.ExaminationRegisterResponse;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class ExaminationRegisterApiResponse {

    private List<String> examinationResult;

    public ExaminationRegisterApiResponse(List<String> examinationResult) {
        this.examinationResult = examinationResult;
    }

    public static ExaminationRegisterApiResponse from(ExaminationRegisterResponse result) {
        return new ExaminationRegisterApiResponse();
    }
}
