package com.ssafy.hanol.global.examinationSurvey;

import com.ssafy.hanol.global.examinationSurvey.dto.ExaminationGenerationApiRequest;
import com.ssafy.hanol.global.examinationSurvey.dto.ExaminationGenerationApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Component
@Slf4j
@RequiredArgsConstructor
public class ExaminationGenerator {

    @Value("${examination_survey.url}")
    private String serverUrl;

    private final WebClient defaultWebClient;

    public ExaminationGenerationApiResponse getExaminationResult(ExaminationGenerationApiRequest request) {
        log.info("[GENERATING-EXAMINATION-RESULT CALL]");
        String url = serverUrl + "/examinations";
        ExaminationGenerationApiResponse result = defaultWebClient.post()
                .uri(url)
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(request))
                .retrieve()
                .bodyToMono(ExaminationGenerationApiResponse.class)
                .block();
        return result;
    }
}
