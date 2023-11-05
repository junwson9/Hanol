package com.ssafy.hanol.member.controller.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.ssafy.hanol.member.service.dto.MemberSignUpResponse;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class MemberSignUpApiResponse {
    private String accessToken;

    public MemberSignUpApiResponse(String accessToken) {
        this.accessToken = accessToken;
    }

    public static MemberSignUpApiResponse from (MemberSignUpResponse memberSignUpResponse){
        return new MemberSignUpApiResponse(memberSignUpResponse.getAccessToken().getToken());
    }
}
