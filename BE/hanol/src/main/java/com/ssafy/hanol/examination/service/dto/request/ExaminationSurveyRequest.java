package com.ssafy.hanol.examination.service.dto.request;

import com.ssafy.hanol.examination.controller.dto.request.ExaminationSurveyApiRequest;
import com.ssafy.hanol.examination.domain.ExaminationSurvey;
import com.ssafy.hanol.member.domain.Member;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Builder
public class ExaminationSurveyRequest {

    private int answer1;
    private int answer2;
    private int answer3;
    private List<Integer> answer4;
    private List<Integer> answer5;
    private int answer6;
    private int answer7;

    private String gender; // 0: MALE, 1: FEMALE
    private int age;
    private Member member;

    public static ExaminationSurveyRequest fromApiRequest(ExaminationSurveyApiRequest apiRequest, String gender, int age, Member member) {
        return ExaminationSurveyRequest.builder()
                .answer1(apiRequest.getAnswer1())
                .answer2(apiRequest.getAnswer2())
                .answer3(apiRequest.getAnswer3())
                .answer4(apiRequest.getAnswer4())
                .answer5(apiRequest.getAnswer5())
                .answer6(apiRequest.getAnswer6())
                .answer7(apiRequest.getAnswer7())
                .gender(gender)
                .age(age)
                .member(member)
                .build();
    }

    public ExaminationSurvey toExaminationSurvey() {
        return ExaminationSurvey.builder()
                .member(member)
                .gender(gender)
                .age(age)
                .answer1(String.valueOf(answer1))
                .answer2(String.valueOf(answer2))
                .answer3(String.valueOf(answer3))
                .answer4(listToString(answer4))
                .answer5(listToString(answer5))
                .answer6(String.valueOf(answer6))
                .answer7(String.valueOf(answer7))
                .build();
    }

    public String listToString(List<Integer> list) {
        return list.stream()
                .map(String::valueOf)
                .collect(Collectors.joining(","));
    }

}
