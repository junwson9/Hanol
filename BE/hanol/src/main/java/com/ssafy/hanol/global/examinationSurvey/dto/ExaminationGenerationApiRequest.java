package com.ssafy.hanol.global.examinationSurvey.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Getter
@ToString
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class ExaminationGenerationApiRequest {

    private int answer1;
    private int answer2;
    private int answer3;
    private List<Integer> answer4;
    private List<Integer> answer5;
    private int answer6;
    private String gender;
    private int age;

    @Builder
    public ExaminationGenerationApiRequest(int answer1, int answer2, int answer3, List<Integer> answer4, List<Integer> answer5, int answer6, String gender, int age) {
        this.answer1 = answer1;
        this.answer2 = answer2;
        this.answer3 = answer3;
        this.answer4 = answer4;
        this.answer5 = answer5;
        this.answer6 = answer6;
        this.gender = gender;
        this.age = age;
    }
}
