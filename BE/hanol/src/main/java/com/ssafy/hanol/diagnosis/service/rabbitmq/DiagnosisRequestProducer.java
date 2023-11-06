package com.ssafy.hanol.diagnosis.service.rabbitmq;

import com.ssafy.hanol.diagnosis.service.dto.request.DiagnosisAiRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class DiagnosisRequestProducer {

    private final RabbitTemplate rabbitTemplate;

    @Value("${rabbitmq.exchange.name}")
    private String exchange;

    @Value("${rabbitmq.queue.request.routing-key}")
    private String requestRoutingKey;

    public void sendDiagnosisRequest(DiagnosisAiRequest request) {

        rabbitTemplate.convertAndSend(exchange, requestRoutingKey, request);
        log.info("두피 진단 요청 전송");
    }
}
