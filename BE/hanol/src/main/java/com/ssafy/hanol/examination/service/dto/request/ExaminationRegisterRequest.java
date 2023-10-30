package com.ssafy.hanol.examination.service.dto.request;

import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
public class ExaminationRegisterRequest {

    private int answer1;

    private int answer2;

    private int answer3;

    private List<Integer> answer4;

    private List<Integer> answer5;

    private int answer6;

    @Builder
    public ExaminationRegisterRequest(int answer1, int answer2, int answer3, List<Integer> answer4, List<Integer> answer5, int answer6) {
        this.answer1 = answer1;
        this.answer2 = answer2;
        this.answer3 = answer3;
        this.answer4 = answer4;
        this.answer5 = answer5;
        this.answer6 = answer6;
    }
}
