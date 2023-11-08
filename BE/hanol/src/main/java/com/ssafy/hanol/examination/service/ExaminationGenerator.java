package com.ssafy.hanol.examination.service;

import com.ssafy.hanol.examination.service.dto.request.ExaminationSurveyRequest;
import com.ssafy.hanol.examination.service.dto.response.ExaminationSurveyResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Component
@Slf4j
@RequiredArgsConstructor
public class ExaminationGenerator {

    private final String serverUrl = "http://fastapi-survey:8001";
//    private final String serverUrl = "http://localhost:8001"; // 로컬 테스트용

    private final WebClient defaultWebClient;

    public ExaminationSurveyResponse getExaminationResult(ExaminationSurveyRequest request) {
        log.info("[EXAMINATION-SURVEY-RESULT CALL]");
        String url = serverUrl + "/examinations";
        List<Integer> result = defaultWebClient.post()
                .uri(url)
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(request))
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<Integer>>() {})
                .block();

        ExaminationSurveyResponse response = ExaminationSurveyResponse.builder()
                .examinationResult(result)
                .build();

        return response;
    }
}
