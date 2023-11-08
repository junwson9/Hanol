package com.ssafy.hanol.member.service.jwt;

import com.ssafy.hanol.common.exception.CustomException;
import com.ssafy.hanol.member.exception.AuthenticationErrorCode;
import com.ssafy.hanol.member.exception.MemberErrorCode;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.security.Key;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.RSAPublicKeySpec;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

@Component
@Slf4j
public class IdTokenResolver {

    private String parseToken(String token) {
        String[] splitToken = token.split("\\.");
        if (splitToken.length != 3) {
            throw new CustomException(AuthenticationErrorCode.INVALID_JWT);
        }
        return splitToken[0] + "." + splitToken[1] + ".";
    }

    public String getKidFromHeader(String token) {
        try {
            Jwt<Header, Claims> headerClaimsJwt = Jwts.parserBuilder()
                                                      .build()
                                                      .parseClaimsJwt(parseToken(token));
            Header header = headerClaimsJwt.getHeader();
            Object kid = header.get("kid");
            if (kid == null) {
                throw new CustomException(MemberErrorCode.INVALID_ID_TOKEN);
            }
            return (String) kid;
        } catch (ExpiredJwtException e) {
            throw new CustomException(MemberErrorCode.EXPIRED_ID_TOKEN);
        } catch (Exception e) {
            throw new CustomException(AuthenticationErrorCode.INVALID_JWT);
        }
    }

    public Map<String, Object> validateIdToken(String idToken, String issuer, String aud, String modulus, String exponent) {
        try {
            Jws<Claims> claimsJws = Jwts.parserBuilder()
                                        .requireAudience(aud)
                                        .requireIssuer(issuer)
                                        .setSigningKey(getRSAPublicKey(modulus, exponent))
                                        .build()
                                        .parseClaimsJws(idToken);
            Claims body = claimsJws.getBody();
            return new HashMap<>(body);
        } catch (Exception e) {
            log.error("ERROR : {}", e.getMessage());
            throw new CustomException(MemberErrorCode.INVALID_ID_TOKEN);
        }
    }

    private Key getRSAPublicKey(String modulus, String exponent)
            throws InvalidKeySpecException, NoSuchAlgorithmException {
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        byte[] decodeN = Base64.getUrlDecoder()
                               .decode(modulus);
        byte[] decodeE = Base64.getUrlDecoder()
                               .decode(exponent);
        BigInteger n = new BigInteger(1, decodeN);
        BigInteger e = new BigInteger(1, decodeE);

        RSAPublicKeySpec keySpec = new RSAPublicKeySpec(n, e);
        return keyFactory.generatePublic(keySpec);
    }
}
