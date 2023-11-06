package com.ssafy.hanol.global.sse.service;

import com.ssafy.hanol.common.exception.CustomException;
import com.ssafy.hanol.global.sse.exception.SseErrorCode;
import com.ssafy.hanol.global.sse.service.dto.response.DiagnoseAiResultResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@Slf4j
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


    /**
     * 이미지 진단 완료 시 클라이언트에게 진단 결과 전달
     */
    public void sendDiagnosisResult(Long memberId, DiagnoseAiResultResponse response) {
        SseEmitter sseEmitter = sseEmitterManager.getSseEmitter(memberId);

        if (sseEmitter == null) {
            throw new CustomException(SseErrorCode.SSE_EMITTER_NOT_FOUND);
        }

        if (!sseEmitterManager.isValidSseEmitter(memberId)) {
            throw new CustomException((SseErrorCode.EXPIRED_SSE_EMITTER));
        }

        try {
            sseEmitter.send(response);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("뭔가 문제가 있음");
        } finally {
            sseEmitter.complete();
            log.info("클라이언트에게 진단 결과 전송 완료");
        }
    }

}
