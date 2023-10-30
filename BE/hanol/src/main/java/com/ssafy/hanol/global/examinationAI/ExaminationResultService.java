package com.ssafy.hanol.global.examinationAI;

import com.ssafy.hanol.examination.service.dto.response.ExaminationRegisterResponse;
import com.ssafy.hanol.global.examinationAI.dto.ExaminationProduceRequest;
import com.ssafy.hanol.global.examinationAI.dto.ExaminationProduceResponse;
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

    @Value("${examination_ai.url}")
    private String serverUrl;

    public ExaminationProduceResponse produceExamination(ExaminationProduceRequest examinationProduceRequest) {
        String url = serverUrl + "/examinations";
        log.info("문진 결과 요청: {}", url);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<ExaminationProduceRequest> entity = new HttpEntity<>(examinationProduceRequest, headers);
        ResponseEntity<ExaminationProduceResponse> response = null;
        try {
            response = restTemplate.exchange(url, HttpMethod.POST, entity, ExaminationProduceResponse.class);
        } catch (Exception e) {
            log.info("Failed to request examination result", e);
        }
        log.info("문진 결과 받아옴: {}", response.getBody());

        return response.getBody();
    }

}
