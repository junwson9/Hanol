package com.ssafy.hanol.examination.service.dto.response;

import lombok.Getter;

import java.util.List;

@Getter
public class ExaminationRegisterResponse {

    private List<String> examinationResult;

    public ExaminationRegisterResponse(List<String> examinationResult) {
        this.examinationResult = examinationResult;
    }

    public static ExaminationRegisterResponse from(List<String> examinationResult) {
        return new ExaminationRegisterResponse(examinationResult);
    }
}
