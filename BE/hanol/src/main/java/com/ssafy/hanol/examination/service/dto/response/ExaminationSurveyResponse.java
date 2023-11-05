package com.ssafy.hanol.examination.service.dto.response;

import com.ssafy.hanol.examination.domain.ExaminationResult;
import com.ssafy.hanol.examination.domain.ExaminationSurvey;
import com.ssafy.hanol.member.domain.Member;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@ToString
public class ExaminationSurveyResponse {

    private List<Integer> examinationResult; // 해당 인덱스 타입에 해당하는 지 순서대로 담은 리스트

    public ExaminationSurveyResponse(List<Integer> examinationResult) {
        this.examinationResult = examinationResult;
    }

    public static ExaminationSurveyResponse from(List<Integer> examinationResult) {
        return new ExaminationSurveyResponse(examinationResult);
    }

    public ExaminationResult toExaminationResult(Member member, Boolean type0, ExaminationSurvey examinationSurvey) {
        return ExaminationResult.builder()
                .member(member)
                .examinationSurvey(examinationSurvey)
                .type0(type0)
                .type1(IntegerToBoolean(examinationResult.get(0)))
                .type2(IntegerToBoolean(examinationResult.get(1)))
                .type3(IntegerToBoolean(examinationResult.get(2)))
                .type4(IntegerToBoolean(examinationResult.get(3)))
                .type5(IntegerToBoolean(examinationResult.get(4)))
                .type6(IntegerToBoolean(examinationResult.get(5)))
                .build();
    }

    public Boolean IntegerToBoolean(Integer value) {
        if (value == null) { return null;}
        return value != 0;
    }
}
