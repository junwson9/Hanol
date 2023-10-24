package com.ssafy.hanol.member.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;


@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
public class OauthId {

    @Column(name = "oauth_id", nullable = false)
    private String oauthId;

    public OauthId(String oauthId) {
        this.oauthId = oauthId;
    }

    public static OauthId of(String oauthId){
        return new OauthId(oauthId);
    }
}
