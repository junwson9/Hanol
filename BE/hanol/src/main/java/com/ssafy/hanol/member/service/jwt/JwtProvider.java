package com.ssafy.hanol.member.service.jwt;


import com.ssafy.hanol.common.exception.CustomException;
import com.ssafy.hanol.member.domain.Member;
import com.ssafy.hanol.member.exception.AuthenticationErrorCode;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;
import org.springframework.util.Base64Utils;

import java.security.Key;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtProvider {

    private final Key accessKey;
    private final Key refreshKey;
    private final Integer accessExpiredMin;
    private final Integer refreshExpiredDay;
    private final String APP_ISSUER = "Junear";

    public JwtProvider(JwtProperty jwtProperty) {
        byte[] accessEncodeByte = Base64Utils.encode(jwtProperty.getAccessKey()
                                                                .getBytes());
        byte[] refreshEncodeByte = Base64Utils.encode(jwtProperty.getRefreshKey()
                                                                 .getBytes());
        this.accessExpiredMin = jwtProperty.getAccessExpiredMin();
        this.refreshExpiredDay = jwtProperty.getRefreshExpiredDay();
        this.accessKey = Keys.hmacShaKeyFor(accessEncodeByte);
        this.refreshKey = Keys.hmacShaKeyFor(refreshEncodeByte);
    }


    public String createAccessToken(Member member) {
        Instant accessExpiredTime = Instant.now()
                                           .plus(this.accessExpiredMin, ChronoUnit.MINUTES);
        Map<String,Object> payload = new HashMap<>();
        payload.put("role", member.getRole().toString());
        return Jwts.builder()
                   .setSubject(member.getId().toString())
                   .setIssuer(APP_ISSUER)
                   .setExpiration(Date.from(accessExpiredTime))
                   .addClaims(payload)
                   .signWith(accessKey)
                   .compact();
    }

    public String createRefreshToken() {
        Instant refreshExpiredTime = Instant.now()
                                            .plus(this.refreshExpiredDay, ChronoUnit.DAYS);
        return Jwts.builder()
                   .setExpiration(Date.from(refreshExpiredTime))
                   .signWith(refreshKey)
                   .compact();
    }


    public boolean validateAccessToken(String token) {
        try {
            Jwts.parserBuilder()
                .setSigningKey(accessKey)
                .build()
                .parseClaimsJws(token);
            return true;
        } catch (ExpiredJwtException e) {
            throw new CustomException(AuthenticationErrorCode.EXPIRED_JWT);
        } catch (Exception e) {
            throw new CustomException(AuthenticationErrorCode.INVALID_JWT);
        }
    }

    public void validateRefreshToken(String refreshToken) {
        try {
            Jwts.parserBuilder()
                .setSigningKey(refreshKey)
                .build()
                .parseClaimsJws(refreshToken);
        }  catch (ExpiredJwtException e) {
            throw new CustomException(AuthenticationErrorCode.EXPIRED_JWT);
        } catch (Exception e) {
            throw new CustomException(AuthenticationErrorCode.INVALID_JWT);
        }
    }


    public Claims getClaimFromExpiredAccessToken(String accessToken) {
        try {
            return Jwts.parserBuilder()
                       .setSigningKey(accessKey)
                       .build()
                       .parseClaimsJws(accessToken)
                       .getBody();
        } catch (ExpiredJwtException e) {
            return e.getClaims();
        } catch (Exception e){
            throw new CustomException(AuthenticationErrorCode.INVALID_JWT);
        }
    }

    public Claims getClaimsFromAccessToken(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(accessKey)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

}
