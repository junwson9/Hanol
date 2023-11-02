package com.ssafy.hanol.notification.service.dto;

import com.ssafy.hanol.notification.service.FcmMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class FcmService {

    public FcmMessage makeMessage(String token, String title, String body, String clickAction) {

        FcmMessage fcmMessage = FcmMessage.builder()
                .message(
                        FcmMessage.Message.builder()
                                .token(token)
                                .notification(
                                        FcmMessage.Notification.builder()
                                                .title(title)
                                                .body(body)
                                                .build()
                                )
                                .data(
                                        FcmMessage.Data.builder()
                                                .title(title)
                                                .body(body)
                                                .clickAction(clickAction)
                                                .build()
                                )
                                .build()
                )
                .validateOnly(false)
                .build();

        return fcmMessage;
    }
}
