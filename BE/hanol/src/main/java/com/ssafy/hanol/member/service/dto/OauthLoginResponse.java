package com.ssafy.hanol.member.service.dto;


import com.ssafy.hanol.member.domain.Token;
import lombok.Getter;

@Getter
public class OauthLoginResponse {
    private final Token accessToken;
    private final Token refreshToken;
    private final String role;

    public OauthLoginResponse(Token accessToken, Token refreshToken, String role) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.role = role;
    }
}
