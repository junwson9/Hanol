package com.ssafy.hanol.notification.service.fcm;

import com.ssafy.hanol.member.domain.Member;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class PushTargetInfo {

    Long memberId;
    String memberName;
    String token;

    public PushTargetInfo(Long memberId, String memberName, String token) {
        this.memberId = memberId;
        this.memberName = memberName;
        this.token = token;
    }
}
