package com.ssafy.hanol.member.service.token;

import com.ssafy.hanol.member.domain.Member;
import com.ssafy.hanol.member.domain.Token;
import com.ssafy.hanol.member.service.jwt.JwtProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import java.util.Optional;


@Component
@Slf4j
public class TokenServiceImpl implements TokenService {

    private final JwtProvider jwtProvider;
    private final ValueOperations<String, String> valueOperations;
    private static final String REDIS_REFRESH_PREFIX = "REFRESH_USER_";


    public TokenServiceImpl(JwtProvider jwtProvider,
                            RedisTemplate<String, String> redisStringTemplate) {
        this.jwtProvider = jwtProvider;
        this.valueOperations = redisStringTemplate.opsForValue();
    }

    @Override
    public Token createAccessToken(Member member) {
        String accessToken = jwtProvider.createAccessToken(member);
        return Token.of(accessToken);
    }

    @Override
    public Token createRefreshToken() {
        String refreshToken = jwtProvider.createRefreshToken();
        return Token.of(refreshToken);
    }

    @Override
    public void saveRefreshToken(Token refreshToken, Long memberId) {
        String refreshKey = getRefreshKey(memberId);
        valueOperations.set(refreshKey, refreshToken.getToken());
    }

    @Override
    public void deleteRefreshToken(Long memberId) {
        valueOperations.getAndDelete(getRefreshKey(memberId));
    }


    @Override
    public Optional<Token> getRefreshTokenFromMember(Member member) {
        String token = valueOperations.get(getRefreshKey(member.getId()));
        return Optional.of(Token.of(token));
    }

    private String getRefreshKey(Long memberId) {
        return REDIS_REFRESH_PREFIX + memberId.toString();
    }
}
