package com.ssafy.hanol.notification.service;

import com.ssafy.hanol.common.exception.CustomException;
import com.ssafy.hanol.member.domain.Member;
import com.ssafy.hanol.member.exception.MemberErrorCode;
import com.ssafy.hanol.member.repository.MemberRepository;
import com.ssafy.hanol.notification.controller.dto.request.FcmTokenApiRequest;
import com.ssafy.hanol.notification.domain.NotificationToken;
import com.ssafy.hanol.notification.repository.NotificationTokenRepository;
import com.ssafy.hanol.notification.service.dto.request.FcmTokenRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class NotificationTokenService {

    private final NotificationTokenRepository notificationTokenRepository;
    private final MemberRepository memberRepository;

    // 클라이언트에서 FCM token을 받아와 갱신
    public void addToken(FcmTokenRequest fcmTokenRequest) {
        Long memberId = fcmTokenRequest.getMemberId();
        String token = fcmTokenRequest.getFcmToken();
        Member member = findMemberByMemberId(memberId);

        // fcm token 으로 NotificationToken 객체 조회. 존재하지 않으면 새로운 객체 생성.
        NotificationToken notificationToken = notificationTokenRepository.findByToken(token)
                .orElseGet(NotificationToken::createEmpty);
        log.info("기존 notificationToken: {}", notificationToken);
        
        // 기존 Notification 객체와 memberId가 다른 경우, 기존 객체를 삭제하고 새로운 객체 생성. (같은 브라우저에서 새로운 회원이 로그인)
        if (isTokenHasDifferentMember(notificationToken, memberId)) {
            notificationTokenRepository.delete(notificationToken);
            notificationToken = NotificationToken.createEmpty();
        }

        // 객체 최신 정보로 업데이트
        notificationToken.changeDetails(member, token, fcmTokenRequest.getDeviceInfo());

        notificationTokenRepository.save(notificationToken);
    }


    // FCM token 삭제
    public void deleteToken(FcmTokenApiRequest fcmTokenApiRequest) {
        notificationTokenRepository.deleteByToken(fcmTokenApiRequest.getFcmToken());
    }

    private Member findMemberByMemberId(Long memberId) {
        return memberRepository.findById(memberId).orElseThrow(() -> new CustomException(MemberErrorCode.NOT_FOUND_MEMBER));
    }

    private boolean isTokenHasDifferentMember(NotificationToken notificationToken, Long memberId) {
        return notificationToken.getMember() != null && !notificationToken.getMember().getId().equals(memberId);
    }




}
