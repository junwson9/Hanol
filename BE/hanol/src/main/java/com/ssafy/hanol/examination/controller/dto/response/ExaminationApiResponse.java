package com.ssafy.hanol.examination.controller.dto.response;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.ssafy.hanol.examination.service.dto.response.ExaminationSurveyResponse;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class ExaminationApiResponse {

    private List<Integer> examinationResult;

    public ExaminationApiResponse(List<Integer> examinationResult) {
        this.examinationResult = examinationResult;
    }

    public static ExaminationApiResponse from(ExaminationSurveyResponse result) {
        return new ExaminationApiResponse(result.getExaminationResult());
    }
}
