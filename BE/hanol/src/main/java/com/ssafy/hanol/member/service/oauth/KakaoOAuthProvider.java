package com.ssafy.hanol.member.service.oauth;


import com.ssafy.hanol.member.service.dto.OidcPublicKeyResponse;
import com.ssafy.hanol.member.service.oidc.OidcPublicKey;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class KakaoOAuthProvider {

    private final WebClient kakaoKAuthWebClient;

    public List<OidcPublicKey> getOidcPublicKeys(){
        log.info("[KAKAO-PUBLIC-KEY CALL]");
        String publicKeyUrl = "/.well-known/jwks.json";
        OidcPublicKeyResponse result = kakaoKAuthWebClient.get()
                                                          .uri(publicKeyUrl)
                                                          .retrieve()
                                                          .bodyToMono(
                                                                     OidcPublicKeyResponse.class)
                                                          .block();
        return result.getKeys();
    }

}
