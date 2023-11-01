package com.ssafy.hanol.global.sse.service;

import com.ssafy.hanol.common.exception.CustomException;
import com.ssafy.hanol.diagnosis.service.dto.response.DiagnosisResponse;
import com.ssafy.hanol.global.sse.exception.SseErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@Service
@RequiredArgsConstructor
public class SseService {

    private final SseEmitterManager sseEmitterManager;

    public SseEmitter createSseEmitter(Long memberId) {
        // 10분 timeout
        SseEmitter sseEmitter = new SseEmitter(100_000L);
        // callback 함수 등록
        sseEmitter.onCompletion(() -> sseEmitterManager.removeSseEmitter(memberId));
        sseEmitter.onTimeout(() -> sseEmitterManager.removeSseEmitter(memberId));

        sseEmitterManager.addSseEmitter(memberId, sseEmitter);

        return sseEmitter;
    }

    public void sendDiagnosisResult(Long memberId, DiagnosisResponse response) {
        SseEmitter sseEmitter = sseEmitterManager.getSseEmitter(memberId);

        if (sseEmitter == null) {
            throw new CustomException(SseErrorCode.SSE_EMITTER_NOT_FOUND);
        }

        
    }

}
