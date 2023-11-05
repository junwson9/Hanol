package com.ssafy.hanol.notification.service;

import com.google.api.core.ApiFuture;
import com.google.firebase.messaging.*;
import com.ssafy.hanol.common.exception.CustomException;
import com.ssafy.hanol.notification.exception.FcmErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
@Slf4j
public class FcmService {

    private final int BATCH_SIZE = 500; // 최대 동시 발송 개수

    /**
     * sendEachAsync() : 개별 메시지 대량 발송(비동기)
     *
     * @param messages
     * @param dryRun : 메시지 유효성만 테스트하려는 경우 true
     */
    public void sendBatchMessage(List<Message> messages, Boolean dryRun) {
        for (int i = 0; i < messages.size(); i += BATCH_SIZE) {
            List<Message> batch = messages.subList(i, Math.min(i + BATCH_SIZE, messages.size()));

            ApiFuture<BatchResponse> sendFuture = FirebaseMessaging.getInstance().sendEachAsync(batch, dryRun);

            try {
                // 비동기로 푸시 요청 후 모든 결과가 반환될 때까지 대기
                BatchResponse response = sendFuture.get();
                // 푸시 요청 결과 로그 출력
                printPushLog(response);

                // TODO 실패 시 처리 로직 추가 (실패 토큰 관리, 재시도 등)
            } catch (InterruptedException | ExecutionException e) {
                throw new CustomException(FcmErrorCode.FCM_SEND_FAIL);
            }
        }
        log.info("푸시 발송 완료");
    }


    /**
     * sendEachForMulticast() : 동일 메시지 대량 발송 (동기)
     *
     * @param message
     * @param dryRun : 메시지 유효성만 테스트하려는 경우 true
     */
    public void sendMulticastMessage(MulticastMessage message, Boolean dryRun) {
        try {
            BatchResponse response = FirebaseMessaging.getInstance().sendEachForMulticast(message, dryRun);
            // TODO 실패 시 처리 로직 추가 (실패 토큰 관리, 재시도 등)
        } catch (FirebaseMessagingException e) {
            throw new CustomException(FcmErrorCode.FCM_SEND_FAIL);
        }
    }


    // 푸시 발송 결과 로그 출력
    private void printPushLog(BatchResponse response) {
        log.info("Successfully sent messages: {}", response.getSuccessCount());
        log.info("Failed to send messages: {}", response.getFailureCount());

        // 실패한 메시지의 오류 내용 출력
        if (response.getFailureCount() > 0) {
            List<SendResponse> responses = response.getResponses();
            for (int idx = 0; idx < responses.size(); idx++) {
                if (!responses.get(idx).isSuccessful()) {
                    log.warn("Failed to send message at index {}: {}", idx, responses.get(idx).getException().getMessage());
                }
            }
        }
    }
}
