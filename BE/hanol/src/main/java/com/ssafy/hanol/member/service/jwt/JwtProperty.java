package com.ssafy.hanol.member.service.jwt;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("jwt")
@Getter
@Setter
@NoArgsConstructor
public class JwtProperty {

    private String accessKey;
    private String refreshKey;
    private Integer accessExpiredMin;
    private Integer refreshExpiredDay;
}
