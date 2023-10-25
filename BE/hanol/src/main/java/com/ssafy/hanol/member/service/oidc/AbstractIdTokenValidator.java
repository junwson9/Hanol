package com.ssafy.hanol.member.service.oidc;


import com.ssafy.hanol.common.exception.CustomException;
import com.ssafy.hanol.member.exception.MemberErrorCode;
import com.ssafy.hanol.member.service.OauthMemberInfo;
import com.ssafy.hanol.member.service.jwt.IdTokenResolver;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Map;

@Slf4j
public abstract class AbstractIdTokenValidator {

    private final OidcIdTokenCheckProperty oidcIdTokenCheckProperty;
    private final IdTokenResolver idTokenResolver;

    public AbstractIdTokenValidator(OidcIdTokenCheckProperty oidcIdTokenCheckProperty,
                                    IdTokenResolver idTokenResolver) {
        this.oidcIdTokenCheckProperty = oidcIdTokenCheckProperty;
        this.idTokenResolver = idTokenResolver;
    }

    public OauthMemberInfo validateIdToken(String idToken) {
        String kid = idTokenResolver.getKidFromHeader(idToken);
        OidcPublicKey publicKey = getPublicKey(kid);

        String issuer = oidcIdTokenCheckProperty.getIssuer();
        String audience = oidcIdTokenCheckProperty.getAudience();
        log.info("idToken : {}", idToken);
        log.info("issuer : {}", issuer);
        log.info("audience : {}", audience);
        log.info("publicKey : {}", publicKey);
        Map<String, Object> payload = idTokenResolver.validateIdToken(idToken, issuer, audience, publicKey.getN(), publicKey.getE());

        return extractMemberInfoFromPayload(payload);
    }

    private OidcPublicKey getPublicKey(String kid) {
        return getOIDCPublicKeys()
                .stream()
                .filter((oidcPublicKey -> oidcPublicKey.getKid()
                                                       .equals(kid)))
                .findAny()
                .orElseThrow(() -> new CustomException(MemberErrorCode.INVALID_ID_TOKEN));
    }

    abstract List<OidcPublicKey> getOIDCPublicKeys();

    abstract OauthMemberInfo extractMemberInfoFromPayload(Map<String, Object> payload);
}
