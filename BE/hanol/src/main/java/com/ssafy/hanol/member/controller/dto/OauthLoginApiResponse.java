package com.ssafy.hanol.member.controller.dto;


import com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.ssafy.hanol.member.service.dto.OauthLoginResponse;
import lombok.Builder;
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
    private String role;

    @Builder
    public OauthLoginApiResponse(String accessToken, String refreshToken, String role) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.role = role;
    }

    public static OauthLoginApiResponse from (OauthLoginResponse oauthLoginResponse){
        return OauthLoginApiResponse.builder()
                .accessToken(oauthLoginResponse.getAccessToken().getToken())
                .refreshToken(oauthLoginResponse.getRefreshToken().getToken())
                .role(oauthLoginResponse.getRole())
                .build();
    }
}
