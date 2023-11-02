package com.ssafy.hanol.member.controller.dto;

import com.ssafy.hanol.member.domain.Gender;
import com.ssafy.hanol.member.service.dto.MemberInfoResponse;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Getter
@NoArgsConstructor
public class MemberInfoApiResponse {

    private String name;
    private String email;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    private String birth;

    @Builder
    public MemberInfoApiResponse(String name, String email, Gender gender, LocalDate birth) {
        this.name = name;
        this.email = email;
        this.gender = gender;
        this.birth = formatDate(birth);
    }

    public static MemberInfoApiResponse from(MemberInfoResponse memberInfoResponse) {
        return MemberInfoApiResponse.builder()
                .name(memberInfoResponse.getName())
                .email(memberInfoResponse.getEmail())
                .gender(memberInfoResponse.getGender())
                .birth(memberInfoResponse.getBirth())
                .build();
    }

    private String formatDate(LocalDate date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd");
        return date.format(formatter);
    }
}
