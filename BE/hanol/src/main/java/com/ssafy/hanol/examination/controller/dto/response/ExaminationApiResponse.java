package com.ssafy.hanol.examination.controller.dto.response;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.ssafy.hanol.examination.domain.ExaminationResult;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class ExaminationApiResponse {

    private Boolean type0;
    private Boolean type1;
    private Boolean type2;
    private Boolean type3;
    private Boolean type4;
    private Boolean type5;
    private Boolean type6;

    public ExaminationApiResponse(Boolean type0, Boolean type1, Boolean type2, Boolean type3, Boolean type4, Boolean type5, Boolean type6) {
        this.type1 = type1;
        this.type0 = type0;
        this.type2 = type2;
        this.type3 = type3;
        this.type4 = type4;
        this.type5 = type5;
        this.type6 = type6;
    }

    public static ExaminationApiResponse from(ExaminationResult result) {
        return new ExaminationApiResponse(result.getType0(), result.getType1(), result.getType2(), result.getType3(), result.getType4(), result.getType5(), result.getType6());
    }
}
