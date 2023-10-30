package com.ssafy.hanol.global.examinationSurvey.dto;

import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@ToString
public class ExaminationGenerationApiResponse {

    private List<Integer> examinationResult; // 해당 인덱스 타입에 해당하는 지 순서대로 담은 리스트
}
