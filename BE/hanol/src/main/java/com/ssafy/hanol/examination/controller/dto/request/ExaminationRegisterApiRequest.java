package com.ssafy.hanol.examination.controller.dto.request;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.ssafy.hanol.examination.service.dto.request.ExaminationRegisterRequest;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class ExaminationRegisterApiRequest {

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

    public ExaminationRegisterRequest toApplicationDto() {
        return ExaminationRegisterRequest.builder()
                .answer1(answer1)
                .answer2(answer2)
                .answer3(answer3)
                .answer4(answer4)
                .answer5(answer5)
                .answer6(answer6)
                .build();
    }

}
