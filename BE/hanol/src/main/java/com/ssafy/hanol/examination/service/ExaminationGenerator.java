package com.ssafy.hanol.examination.service;

import com.ssafy.hanol.examination.service.dto.request.ExaminationSurveyRequest;
import com.ssafy.hanol.examination.service.dto.response.ExaminationSurveyResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

@Component
@Slf4j
@RequiredArgsConstructor
public class ExaminationGenerator {

    private final String serverUrl = "fastapi-survey:8001";
//    private final String serverUrl = "localhost:8001"; // 로컬 테스트용

    private final WebClient defaultWebClient;

    public ExaminationSurveyResponse getExaminationResult(ExaminationSurveyRequest request) {
        log.info("[EXAMINATION-SURVEY-RESULT CALL]");
        String url = serverUrl + "/examinations";
        ExaminationSurveyResponse result = defaultWebClient.post()
                .uri(url)
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(request))
                .retrieve()
                .bodyToMono(ExaminationSurveyResponse.class)
                .block();
        return result;
    }
}
