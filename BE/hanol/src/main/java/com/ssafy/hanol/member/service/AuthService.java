package com.ssafy.hanol.member.service;

import com.ssafy.hanol.member.domain.Member;
import com.ssafy.hanol.member.domain.Token;
import com.ssafy.hanol.member.repository.MemberRepository;
import com.ssafy.hanol.member.service.dto.OauthLoginRequest;
import com.ssafy.hanol.member.service.dto.OauthLoginResponse;
import com.ssafy.hanol.member.service.oidc.IdTokenValidator;
import com.ssafy.hanol.member.service.token.TokenService;
import com.ssafy.hanol.notification.domain.NotificationConfiguration;
import com.ssafy.hanol.notification.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class AuthService {
    private final IdTokenValidator idTokenValidator;
    private final MemberRepository memberRepository;
    private final TokenService tokenService;
    private final NotificationRepository notificationRepository;

    public OauthLoginResponse login(OauthLoginRequest oauthLoginRequest) {
        OauthMemberInfo oauthMemberInfo = idTokenValidator.validateIdToken(
                oauthLoginRequest.getIdToken(), oauthLoginRequest.getOauthProvider());

        Member oauthMember = memberRepository.findByOauthIdAndProvider(
                        oauthMemberInfo.getOauthId(),
                        oauthMemberInfo.getOauthProvider())
                .orElse(null);

        if (isRequireSignUp(oauthMember)) {
            oauthMember = memberRepository.save(oauthMemberInfo.toMember());
            // notification_configuration 테이블에 해당 회원 정보 등록
            NotificationConfiguration notificationConfiguration = NotificationConfiguration.fromMember(oauthMember);
            notificationRepository.save(notificationConfiguration);
        }

        Token accessToken = tokenService.createAccessToken(oauthMember);
        Token refreshToken = tokenService.createRefreshToken();
        tokenService.saveRefreshToken(refreshToken, oauthMember.getId());
        return new OauthLoginResponse(accessToken, refreshToken);
    }

    private boolean isRequireSignUp(Member oauthMember) {
        return oauthMember == null;
    }
}
