package com.ssafy.hanol.diagnosis.service.rabbitmq;

import com.ssafy.hanol.diagnosis.service.DiagnosisService;
import com.ssafy.hanol.diagnosis.service.dto.response.RabbitmqResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class DiagnosisResponseConsumer {

    private final DiagnosisService diagnosisService;

    @RabbitListener(queues = "${rabbitmq.queue.response.name}")
    public void receiveDiagnosis(RabbitmqResponse response) {
        log.info("response 큐에서 요청 받음");
        log.info("sse id : {}", response.getSseId());
        log.info("value 1 : {}", response.getValue1());
        log.info("value 2 : {}", response.getValue2());
        log.info("value 3 : {}", response.getValue3());
        log.info("value 4 : {}", response.getValue4());
        log.info("value 5 : {}", response.getValue5());
        log.info("value 6 : {}", response.getValue6());

        try {
            diagnosisService.saveDiagnosisAndSend(response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
