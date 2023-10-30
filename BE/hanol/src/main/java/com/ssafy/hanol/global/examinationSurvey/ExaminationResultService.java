package com.ssafy.hanol.global.examinationSurvey;

import com.ssafy.hanol.global.examinationSurvey.dto.ExaminationGenerationApiRequest;
import com.ssafy.hanol.global.examinationSurvey.dto.ExaminationGenerationApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
@RequiredArgsConstructor
public class ExaminationResultService {

    private final RestTemplate restTemplate;

    @Value("${examination_survey.url}")
    private String serverUrl;

    public ExaminationGenerationApiResponse generateExaminationResult(ExaminationGenerationApiRequest examinationGenerationApiRequest) {
        String url = serverUrl + "/examinations";
        log.info("문진 결과 요청: {}", url);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<ExaminationGenerationApiRequest> entity = new HttpEntity<>(examinationGenerationApiRequest, headers);
        ResponseEntity<ExaminationGenerationApiResponse> response = null;
        try {
            response = restTemplate.exchange(url, HttpMethod.POST, entity, ExaminationGenerationApiResponse.class);
        } catch (Exception e) {
            log.info("Failed to request examination result", e);
        }
        log.info("문진 결과 받아옴: {}", response.getBody());

        return response.getBody();
    }

}
