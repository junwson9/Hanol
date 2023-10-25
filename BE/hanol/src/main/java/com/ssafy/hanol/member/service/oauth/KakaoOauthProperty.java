package com.ssafy.hanol.member.service.oauth;


import com.ssafy.hanol.member.service.oidc.OidcIdTokenCheckProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("oauth.kakao")
@Getter
@Setter
public class KakaoOauthProperty {

    private String clientId;
    private String adminKey;
    private String issuer;

    public OidcIdTokenCheckProperty toIdTokenProperty(){
        return new OidcIdTokenCheckProperty(issuer, clientId);
    }
 }
