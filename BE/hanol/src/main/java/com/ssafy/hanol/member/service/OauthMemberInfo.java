package com.ssafy.hanol.member.service;

import com.ssafy.hanol.member.domain.Member;
import com.ssafy.hanol.member.domain.OauthId;
import com.ssafy.hanol.member.domain.OauthProvider;
import com.ssafy.hanol.member.domain.Role;
import lombok.Builder;
import lombok.Getter;


@Getter
public class OauthMemberInfo {

    private OauthId oauthId;
    private OauthProvider oauthProvider;
    private String name;

    @Builder
    public OauthMemberInfo(String oauthId, OauthProvider oauthProvider, String name) {
        this.oauthId = OauthId.of(oauthId);
        this.oauthProvider = oauthProvider;
        this.name = name;
    }

    public Member toMember() {
        return Member.builder()
                     .oauthProvider(oauthProvider)
                     .name(name)
                     .oauthId(oauthId)
                     .role(Role.GUEST)
                     .build();
    }
}
