package com.ssafy.hanol.member.service.dto;

import com.ssafy.hanol.member.domain.Token;
import lombok.Getter;

@Getter
public class MemberSignUpResponse {

    private final Token accessToken;

    public MemberSignUpResponse(Token accessToken) {
        this.accessToken = accessToken;
    }
}
