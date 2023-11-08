package com.ssafy.hanol.member.service.oidc;


import com.ssafy.hanol.member.domain.OauthProvider;
import com.ssafy.hanol.member.service.OauthMemberInfo;

public interface IdTokenValidator {
    OauthMemberInfo validateIdToken(String idToken, OauthProvider oauthProvider);
}
