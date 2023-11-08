package com.ssafy.hanol.global.config.auth;


import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class AuthMember {

    private Long id;
    private List<String> roles;

    public AuthMember(Long id, Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.roles = authorities.stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
    }
}
