package com.ssafy.hanol.member.service.oidc;


import com.ssafy.hanol.common.exception.CommonErrorCode;
import com.ssafy.hanol.common.exception.CustomException;
import com.ssafy.hanol.member.domain.OauthProvider;
import com.ssafy.hanol.member.repository.RedisCacheRepository;
import com.ssafy.hanol.member.service.oauth.KakaoOAuthProvider;
import com.ssafy.hanol.member.service.oauth.KakaoOauthProperty;
import com.ssafy.hanol.member.service.OauthMemberInfo;
import com.ssafy.hanol.member.service.jwt.IdTokenResolver;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;


@Component
public class KakaoIdTokenValidator extends AbstractIdTokenValidator {

    private final KakaoOAuthProvider kakaoOAuthProvider;
    private final String KAKAO_ID_KEY = "sub";
    private final String KAKAO_NAME_KEY = "nickname";
    private final String KAKAO_PROFILE_EMAIL_KEY = "email";
    private final RedisCacheRepository redisCacheRepository;
    private final String  KAKAO_JWKS_CACHE_KEY = "kakao-jwks";

    public KakaoIdTokenValidator(KakaoOauthProperty kakaoOauthProperty,
                                 KakaoOAuthProvider kakaoOAuthProvider, IdTokenResolver idTokenResolver,
                                 RedisCacheRepository repository) {
        super(kakaoOauthProperty.toIdTokenProperty(), idTokenResolver);
        this.kakaoOAuthProvider = kakaoOAuthProvider;
        this.redisCacheRepository = repository;
    }

    @Override
    List<OidcPublicKey> getOIDCPublicKeys() {
        List<OidcPublicKey> oidcPublicKeys = redisCacheRepository.getOIDCPublicKeys(KAKAO_JWKS_CACHE_KEY);
        if(oidcPublicKeys == null){
            List<OidcPublicKey> publicKeys = kakaoOAuthProvider.getOidcPublicKeys();
            redisCacheRepository.savePublicKey(KAKAO_JWKS_CACHE_KEY,publicKeys);
            oidcPublicKeys = publicKeys;
        }
        return  oidcPublicKeys;
    }

    @Override
    OauthMemberInfo extractMemberInfoFromPayload(Map<String, Object> payload) {
        String oauthId = (String) payload.get(KAKAO_ID_KEY);
        String name = (String) payload.get(KAKAO_NAME_KEY);
        if (requireValueIsNull(oauthId, name)) {
            throw new CustomException(CommonErrorCode.SERVER_ERROR);
        }

        return OauthMemberInfo.builder()
                              .oauthId(oauthId)
                              .name(name)
                              .oauthProvider(OauthProvider.KAKAO)
                              .build();
    }


    /*
     * 해당 예외가 발생하는건 카카오에서 프로퍼티 key 값을 바꾸지 않는 이상은 발생하지 않는다.
     */
    private boolean requireValueIsNull(String oauthId, String name) {
        return oauthId == null || name == null;
    }
}
