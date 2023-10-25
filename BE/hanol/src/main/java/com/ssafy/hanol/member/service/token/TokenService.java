package com.ssafy.hanol.member.service.token;

import com.ssafy.hanol.member.domain.Member;
import com.ssafy.hanol.member.domain.Token;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public interface TokenService {

    Token createAccessToken(Member member);
    Token createRefreshToken();
    void saveRefreshToken(Token refreshToken, Long memberId);

    void deleteRefreshToken(Long memberId);

    Optional<Token> getRefreshTokenFromMember(Member member);
}
