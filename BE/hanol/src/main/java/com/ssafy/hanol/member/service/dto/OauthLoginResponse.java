package com.ssafy.hanol.member.service.dto;


import com.ssafy.hanol.member.domain.Token;
import lombok.Getter;

@Getter
public class OauthLoginResponse {
    private final Token accessToken;
    private final Token refreshToken;

    public OauthLoginResponse(Token accessToken, Token refreshToken) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }
}
