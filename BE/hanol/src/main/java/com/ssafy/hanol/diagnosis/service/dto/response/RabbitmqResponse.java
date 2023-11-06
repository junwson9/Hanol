package com.ssafy.hanol.diagnosis.service.dto.response;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class RabbitmqResponse {
    private Long keyId;
    private Long sseId;
    private int value1;
    private int value2;
    private int value3;
    private int value4;
    private int value5;
    private int value6;

    public RabbitmqResponse(Long keyId, Long sseId, int value1, int value2, int value3, int value4, int value5, int value6) {
        this.keyId = keyId;
        this.sseId = sseId;
        this.value1 = value1;
        this.value2 = value2;
        this.value3 = value3;
        this.value4 = value4;
        this.value5 = value5;
        this.value6 = value6;
    }
}
