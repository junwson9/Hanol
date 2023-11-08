package com.ssafy.hanol.notification.service.fcm;

import lombok.Getter;

@Getter
public enum PushMessageType {

    CHECK_ROUTINE("오늘의 루틴 체크 완료하셨나요?", "하루 하루 꾸준한 실천으로 한올 한올 건강한 모발을 만들어요!", "/"),
    EACH_ROUTINE("하실 시간이에요!", "건강한 두피를 위해 지금 실천하고 기록을 남겨주세요!", "/");

    private final String titleTemplate;
    private final String body;
    private final String clickAction;

    PushMessageType(String titleTemplate, String body, String clickAction) {
        this.titleTemplate = titleTemplate;
        this.body = body;
        this.clickAction = clickAction;
    }

    public String getTitle(String memberName, String routineName) {
        return memberName + "님 " + routineName + titleTemplate;
    }
}
