package com.ssafy.hanol.member.service.oidc;

import com.ssafy.hanol.common.exception.CustomException;
import com.ssafy.hanol.member.domain.OauthProvider;
import com.ssafy.hanol.member.exception.MemberErrorCode;
import com.ssafy.hanol.member.service.OauthMemberInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class IdTokenValidatorImpl implements IdTokenValidator {

    private final AbstractIdTokenValidator kakaoIdTokenValidator;

    @Override
    public OauthMemberInfo validateIdToken(String idToken, OauthProvider oauthProvider) {
        if (oauthProvider.equals(OauthProvider.KAKAO)) {
            return kakaoIdTokenValidator.validateIdToken(idToken);
        } else {
            throw new CustomException(MemberErrorCode.NOT_SUPPORT_OAUTH_PROVIDER);
        }
    }
}
