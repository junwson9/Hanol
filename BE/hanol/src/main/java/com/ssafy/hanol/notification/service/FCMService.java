package com.ssafy.hanol.notification.service;

import com.ssafy.hanol.member.domain.Member;
import com.ssafy.hanol.member.repository.MemberRepository;
import com.ssafy.hanol.notification.domain.NotificationToken;
import com.ssafy.hanol.notification.repository.FCMRepository;
import com.ssafy.hanol.notification.service.dto.request.FCMTokenRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class FCMService {

    private final FCMRepository fcmRepository;
    private final MemberRepository memberRepository;

    public void addToken(FCMTokenRequest fcmTokenRequest) {
        Long memberId = fcmTokenRequest.getMemberId();
        String token = fcmTokenRequest.getFcmToken();

        Member member = memberRepository.findById(memberId).orElseThrow(); // TODO 예외처리

        // fcm token 으로 NotificationToken 객체 조회. 존재하지 않으면 새로운 객체 생성.
        NotificationToken notificationToken = fcmRepository.findByToken(token)
                .orElseGet(NotificationToken::createEmpty);
        log.info("기존 notificationToken: {}", notificationToken);
        
        // 기존 Notification 객체와 memberId가 다른 경우, 기존 객체를 삭제하고 새로운 객체 생성.
        if (isTokenHasDifferentMember(notificationToken, memberId)) {
            fcmRepository.delete(notificationToken);
            notificationToken = NotificationToken.createEmpty();
            log.info("같은 브라우저에서 새로운 회원이 로그인하여 기존 notificationToken 삭제 후 새로 생성함");
        }

        // 객체 최신 정보로 업데이트
        notificationToken.changeDetails(member, token, fcmTokenRequest.getDeviceInfo());

        fcmRepository.save(notificationToken);
    }


    private boolean isTokenHasDifferentMember(NotificationToken notificationToken, Long memberId) {
        return notificationToken.getMember() != null && !notificationToken.getMember().getId().equals(memberId);
    }


    public void removeToken(FCMTokenRequest fcmTokenRequest) {
        fcmRepository.deleteByToken(fcmTokenRequest.getFcmToken());
    }

}
