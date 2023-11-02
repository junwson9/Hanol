package com.ssafy.hanol.member.service.dto;

import com.ssafy.hanol.member.domain.Token;
import lombok.Getter;

@Getter
public class JwtInfo {
    private final Token accessToken;
    private final Token refreshToken;

    public JwtInfo(Token accessToken, Token refreshToken) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }

}
