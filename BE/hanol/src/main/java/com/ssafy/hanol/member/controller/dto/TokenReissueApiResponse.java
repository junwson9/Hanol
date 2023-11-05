package com.ssafy.hanol.member.controller.dto;


import com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@JsonNaming(SnakeCaseStrategy.class)
public class TokenReissueApiResponse {

    private String accessToken;

    public TokenReissueApiResponse(String accessToken) {
        this.accessToken = accessToken;
    }
}
