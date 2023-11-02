package com.ssafy.hanol.notification.service;

import com.ssafy.hanol.member.domain.Member;
import lombok.Getter;

@Getter
public class PushTargetInfo {

    Member member;
    String token;

    public PushTargetInfo(Member member, String token) {
        this.member = member;
        this.token = token;
    }
}
