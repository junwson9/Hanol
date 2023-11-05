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

    @NotNull(message = "질문1에 대한 대답은 필수 값입니다.")
    private int answer1;

    @NotNull(message = "질문2에 대한 대답은 필수 값입니다.")
    private int answer2;

    @NotNull(message = "질문3에 대한 대답은 필수 값입니다.")
    private int answer3;

    @NotNull(message = "질문4에 대한 대답은 필수 값입니다.")
    private List<Integer> answer4;

    @NotNull(message = "질문5에 대한 대답은 필수 값입니다.")
    private List<Integer> answer5;

    @NotNull(message = "질문6에 대한 대답은 필수 값입니다.")
    private int answer6;

    @NotNull(message = "질문7에 대한 대답은 필수 값입니다.")
    private int answer7;

}
