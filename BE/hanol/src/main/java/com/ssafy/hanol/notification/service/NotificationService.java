package com.ssafy.hanol.notification.service;

import com.ssafy.hanol.notification.domain.NotificationSetting;
import com.ssafy.hanol.notification.domain.NotificationType;
import com.ssafy.hanol.notification.repository.NotificationRepository;
import com.ssafy.hanol.notification.service.dto.request.NotificationModifyRequest;
import com.ssafy.hanol.notification.service.dto.response.NotificationResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class NotificationService {

    private final NotificationRepository notificationRepository;

    public NotificationResponse findNotifications() {
        // 임시데이터
        Long memberId = 1L;
        NotificationResponse notificationResponse = notificationRepository.findNotificationResponseByMemberId(memberId).orElseThrow();
        return notificationResponse;
    }

    public NotificationResponse modifyNotification(NotificationModifyRequest notificationModifyRequest) {
        // 임시데이터
        Long memberId = 1L;

        NotificationSetting notificationSetting = notificationRepository.findByMemberId(memberId).orElseThrow();
        NotificationType notificationType = notificationModifyRequest.getNotificationType();
        switch (notificationType) {
            case CHECK_ROUTINE:
                notificationSetting.updateIsCheckRoutineActive(notificationModifyRequest.getIsActive());
                break;
            case INDIVIDUAL_ROUTINE:
                notificationSetting.updateIsIndividualRoutineActive(notificationModifyRequest.getIsActive());
                break;
        }

        return NotificationResponse.builder()
                .notificationSettingId(notificationSetting.getNotificationSettingId())
                .memberId(memberId)
                .isCheckRoutineActive(notificationSetting.getIsCheckRoutineActive())
                .isIndividualRoutineActive(notificationSetting.getIsIndividualRoutineActive())
                .build();
    }
}
