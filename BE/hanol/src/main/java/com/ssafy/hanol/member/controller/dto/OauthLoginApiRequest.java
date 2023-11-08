package com.ssafy.hanol.member.controller.dto;


import com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.ssafy.hanol.member.domain.OauthProvider;
import com.ssafy.hanol.member.service.dto.OauthLoginRequest;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@JsonNaming(SnakeCaseStrategy.class)
public class OauthLoginApiRequest {

    @NotBlank
    private String idToken;

    @NotNull
    private OauthProvider oauthProvider;

    public OauthLoginRequest toApplicationDto() {
        return OauthLoginRequest.builder()
                                .idToken(idToken)
                                .oauthProvider(oauthProvider)
                                .build();
    }
}
