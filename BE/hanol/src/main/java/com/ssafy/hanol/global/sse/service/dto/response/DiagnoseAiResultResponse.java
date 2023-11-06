package com.ssafy.hanol.global.sse.service.dto.response;

import com.ssafy.hanol.diagnosis.domain.Diagnosis;
import com.ssafy.hanol.diagnosis.service.dto.response.RabbitmqResponse;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DiagnoseAiResultResponse {
    private String imageUrl;
    private int value1;
    private int value2;
    private int value3;
    private int value4;
    private int value5;
    private int value6;

    @Builder
    public DiagnoseAiResultResponse(String imageUrl, int value1, int value2, int value3, int value4, int value5, int value6) {
        this.imageUrl = imageUrl;
        this.value1 = value1;
        this.value2 = value2;
        this.value3 = value3;
        this.value4 = value4;
        this.value5 = value5;
        this.value6 = value6;
    }

    public static DiagnoseAiResultResponse from(Diagnosis diagnosis) {
        return DiagnoseAiResultResponse.builder()
                .imageUrl(diagnosis.getImageUrl())
                .value1(diagnosis.getValue1())
                .value2(diagnosis.getValue2())
                .value3(diagnosis.getValue3())
                .value4(diagnosis.getValue4())
                .value5(diagnosis.getValue5())
                .value6(diagnosis.getValue6())
                .build();
    }

}
