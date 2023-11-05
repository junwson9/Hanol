package com.ssafy.hanol.member.controller.dto;


import com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@JsonNaming(SnakeCaseStrategy.class)
public class TokenReissueApiRequest {

    @NotBlank
    private String refreshToken;

}
