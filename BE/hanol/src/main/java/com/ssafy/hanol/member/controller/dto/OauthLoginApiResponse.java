package com.ssafy.hanol.member.controller.dto;


import com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.ssafy.hanol.member.service.dto.OauthLoginResponse;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
@Setter
@JsonNaming(SnakeCaseStrategy.class)
public class OauthLoginApiResponse {

    private String accessToken;
    private String refreshToken;

    public OauthLoginApiResponse(String accessToken, String refreshToken) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }

    public static OauthLoginApiResponse from (OauthLoginResponse oauthLoginResponse){
        return new OauthLoginApiResponse(oauthLoginResponse.getAccessToken().getToken(), oauthLoginResponse.getRefreshToken().getToken());
    }
}
