package com.ssafy.hanol.member.service.dto;

import com.ssafy.hanol.member.domain.OauthProvider;
import lombok.Builder;
import lombok.Getter;

@Getter
public class OauthLoginRequest {

    private final String idToken;
    private final OauthProvider oauthProvider;


    @Builder
    public OauthLoginRequest(String idToken, OauthProvider oauthProvider) {
        this.idToken = idToken;
        this.oauthProvider = oauthProvider;
    }

}
