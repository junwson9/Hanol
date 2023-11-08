package com.ssafy.hanol.member.service.dto;

import com.ssafy.hanol.member.domain.Gender;
import com.ssafy.hanol.member.domain.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDate;
import java.util.Date;

@Getter
@NoArgsConstructor
public class MemberInfoResponse {

    private String name;
    private String email;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    private LocalDate birth;

    @Builder
    public MemberInfoResponse(String name, String email, Gender gender, LocalDate birth) {
        this.name = name;
        this.email = email;
        this.gender = gender;
        this.birth = birth;
    }

    public static MemberInfoResponse from(Member member) {
        return MemberInfoResponse.builder()
                .name(member.getName())
                .email(member.getEmail())
                .gender(member.getGender())
                .birth(member.getBirth())
                .build();
    }
}
