package com.ssafy.hanol.member.service.dto;


import com.ssafy.hanol.member.domain.Token;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class TokenReissueResponse {

    private Token accessToken;

    public TokenReissueResponse(Token accessToken) {
        this.accessToken = accessToken;
    }
}
