package com.ssafy.hanol.diagnosis.service.rabbitmq;

import com.ssafy.hanol.diagnosis.service.DiagnosisService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class DiagnosisResponseConsumer {

    private final DiagnosisService diagnosisService;

    @RabbitListener(queues = "${rabbitmq.queue.request.name")
    public void receiveDiagnosis() {
        log.info("response 큐에서 요청 받음");
    }
}
