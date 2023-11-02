package com.ssafy.hanol.member.service.dto;


import com.ssafy.hanol.member.domain.Gender;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Getter
@NoArgsConstructor
public class MemberSignUpRequest {

    private Gender gender;
    private LocalDate birth;


    @Builder
    public MemberSignUpRequest(Gender gender, LocalDate birth) {
        this.gender = gender;
        this.birth = birth;
    }
}
