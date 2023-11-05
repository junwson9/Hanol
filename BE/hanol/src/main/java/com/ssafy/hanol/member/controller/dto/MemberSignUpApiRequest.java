package com.ssafy.hanol.member.controller.dto;

import com.ssafy.hanol.member.domain.Gender;
import com.ssafy.hanol.member.service.dto.MemberSignUpRequest;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class MemberSignUpApiRequest {

    @NotNull(message = "성별은 필수 값입니다.")
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @NotNull(message = "성별은 필수 값입니다.")
    private LocalDate birth;

    @Builder
    public MemberSignUpApiRequest(Gender gender, LocalDate birth) {
        this.gender = gender;
        this.birth = birth;
    }

    public MemberSignUpRequest toMemberSigUpInfo() {
        return MemberSignUpRequest.builder()
                .gender(gender)
                .birth(birth)
                .build();
    }
}
