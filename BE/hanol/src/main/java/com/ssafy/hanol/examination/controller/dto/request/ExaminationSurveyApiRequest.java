package com.ssafy.hanol.examination.controller.dto.request;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class ExaminationSurveyApiRequest {

    @NotNull
    private int answer1;

    @NotNull
    private int answer2;

    @NotNull
    private int answer3;

    @NotNull
    private List<Integer> answer4;

    @NotNull
    private List<Integer> answer5;

    @NotNull
    private int answer6;

    @NotNull
    private int answer7;

}
