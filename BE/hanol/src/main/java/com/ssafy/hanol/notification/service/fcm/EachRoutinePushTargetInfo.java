package com.ssafy.hanol.notification.service.fcm;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class EachRoutinePushTargetInfo {

    Long memberId;
    String memberName;
    String token;
    Long routineId;
    String routineName;

    public EachRoutinePushTargetInfo(Long memberId, String memberName, String token, Long routineId, String routineName) {
        this.memberId = memberId;
        this.memberName = memberName;
        this.token = token;
        this.routineId = routineId;
        this.routineName = routineName;
    }
}
