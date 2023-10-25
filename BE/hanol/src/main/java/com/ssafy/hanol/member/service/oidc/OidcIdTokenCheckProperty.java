package com.ssafy.hanol.member.service.oidc;

import lombok.Getter;


@Getter
public class OidcIdTokenCheckProperty {

    // 발행 회사 특정 url
    private String issuer;

    // client-id
    private String audience;

    public OidcIdTokenCheckProperty(String issuer, String audience) {
        this.issuer = issuer;
        this.audience = audience;
    }
}
