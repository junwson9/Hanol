package com.ssafy.hanol.member.service;

import com.ssafy.hanol.common.exception.CustomException;
import com.ssafy.hanol.member.domain.Member;
import com.ssafy.hanol.member.domain.Role;
import com.ssafy.hanol.member.domain.Token;
import com.ssafy.hanol.member.exception.AuthenticationErrorCode;
import com.ssafy.hanol.member.exception.MemberErrorCode;
import com.ssafy.hanol.member.repository.MemberRepository;
import com.ssafy.hanol.member.service.dto.OauthLoginRequest;
import com.ssafy.hanol.member.service.dto.OauthLoginResponse;
import com.ssafy.hanol.member.service.dto.TokenReissueRequest;
import com.ssafy.hanol.member.service.dto.TokenReissueResponse;
import com.ssafy.hanol.member.service.oidc.IdTokenValidator;
import com.ssafy.hanol.member.service.token.TokenService;
import com.ssafy.hanol.notification.controller.dto.request.FcmTokenApiRequest;
import com.ssafy.hanol.notification.domain.NotificationConfiguration;
import com.ssafy.hanol.notification.repository.NotificationRepository;
import com.ssafy.hanol.notification.service.NotificationTokenService;
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
    private final NotificationTokenService notificationTokenService;

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
        oauthMember.updateLastLoginDate();

        Token accessToken = tokenService.createAccessToken(oauthMember);
        Token refreshToken = tokenService.createRefreshToken();
        tokenService.saveRefreshToken(refreshToken, oauthMember.getId());

        return new OauthLoginResponse(accessToken, refreshToken, Role.GUEST.toString());
    }

    public TokenReissueResponse reissue(TokenReissueRequest request){
        Member member = findMemberByMemberId(request.getMemberId());
        Token token = tokenService.getRefreshTokenFromMember(member)
                .orElseThrow(() -> new CustomException(
                        AuthenticationErrorCode.REQUIRE_LOGIN));
        if(!isCorrespondToken(request.getRefreshToken(), token)){
            throw new CustomException(AuthenticationErrorCode.UN_AUTHORIZATION);
        }
        return new TokenReissueResponse(tokenService.createAccessToken(member));
    }

    public void logout(Long memberId, FcmTokenApiRequest fcmTokenApiRequest){
        tokenService.deleteRefreshToken(memberId);
        notificationTokenService.deleteToken(fcmTokenApiRequest);
    }

    private boolean isRequireSignUp(Member oauthMember) {
        return oauthMember == null;
    }

    private boolean isCorrespondToken(Token inputToken, Token savedToken){
        return inputToken.getToken().equals(savedToken.getToken());
    }

    private Member findMemberByMemberId(Long memberId) {
        return memberRepository.findById(memberId).orElseThrow(() -> new CustomException(MemberErrorCode.NOT_FOUND_MEMBER));
    }
}
