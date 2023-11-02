package com.ssafy.hanol.notification.service;

import com.google.firebase.messaging.*;
import com.ssafy.hanol.common.exception.CustomException;
import com.ssafy.hanol.notification.exception.FcmErrorCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
@RequiredArgsConstructor
public class FcmService {

    public void sendMulticastMessage(FcmMulticastMessageRequest request) {
        // DTO를 FCM에 보낼 수 있는 message 형태로 변환
        MulticastMessage message = request.toMulticastMessage();

        try {
            BatchResponse response = FirebaseMessaging.getInstance().sendMulticast(message);
            if (response.getFailureCount() > 0) {
                handleFailedTokens(response, request.getTokens());
            }
        } catch (FirebaseMessagingException e) {
            throw new CustomException(FcmErrorCode.SEND_MULTICAST_ERROR);
        }
    }

    /**
     * 발송에 실패한 토큰
     */
    private void handleFailedTokens(BatchResponse response, List<String> tokens) {
        List<SendResponse> responses = response.getResponses();
        List<String> failedTokens = new ArrayList<>();

        for (int i = 0; i < responses.size(); i++) {
            if (!responses.get(i).isSuccessful()) {
                failedTokens.add(tokens.get(i));
            }
        }
        log.error("List of tokens that caused failures: " + failedTokens);
        // TODO 실패한 토큰 원인 파악 후 DB에서 삭제
    }


//    public BaseFcmMessage makeMulticastMessage(List<String> tokens, String title, String body, String clickAction) {
//        BaseFcmMessage fcmMessage = FcmMulticastMessageRequest.builder()
//                .tokens(tokens)
//                .notification(BaseFcmMessage.Notification.builder()
//                        .title(title)
//                        .body(body)
//                        .build())
//                .data(BaseFcmMessage.Data.builder()
//                        .title(title)
//                        .body(body)
//                        .clickAction(clickAction)
//                        .build())
//                .build();
//        return fcmMessage;
//    }
//
//
//    public BaseFcmMessage makeMessage(String token, String title, String body, String clickAction) {
//
//        BaseFcmMessage fcmMessage = FcmMessage.builder()
//                .token(token)
//                .notification(
//                        FcmMessage.Notification.builder()
//                                .title(title)
//                                .body(body)
//                                .build())
//                .data(
//                        FcmMessage.Data.builder()
//                                .title(title)
//                                .body(body)
//                                .clickAction(clickAction)
//                                .build())
//                .build();
//        return fcmMessage;
//    }

}
