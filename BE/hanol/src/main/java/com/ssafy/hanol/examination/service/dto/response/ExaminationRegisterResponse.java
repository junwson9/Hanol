package com.ssafy.hanol.examination.service.dto.response;

import lombok.Getter;

import java.util.List;

@Getter
public class ExaminationRegisterResponse {

    private List<Integer> examinationResult;

    public ExaminationRegisterResponse(List<Integer> examinationResult) {
        this.examinationResult = examinationResult;
    }

    public static ExaminationRegisterResponse from(List<Integer> examinationResult) {
        return new ExaminationRegisterResponse(examinationResult);
    }
}
