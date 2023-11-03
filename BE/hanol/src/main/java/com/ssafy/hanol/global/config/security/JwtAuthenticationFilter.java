package com.ssafy.hanol.global.config.security;

import com.ssafy.hanol.common.exception.CustomException;
import com.ssafy.hanol.member.exception.AuthenticationErrorCode;
import com.ssafy.hanol.member.service.jwt.JwtProvider;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private static final String AUTHORIZATION_HEADER = "Authorization";
    private static final String HEADER_PREFIX = "Bearer ";

    private final JwtProvider jwtProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
            FilterChain filterChain) throws ServletException, IOException {
        String accessToken = extractToken(request);

        if (isRefreshApi(request)) {
            if(!StringUtils.hasText(accessToken)){
                throw new CustomException(AuthenticationErrorCode.INVALID_JWT);
            }

            Claims claims = jwtProvider.getClaimFromExpiredAccessToken(accessToken);
            setAuthenticationToContext(claims);

            filterChain.doFilter(request, response);
            return;
        }

        if (StringUtils.hasText(accessToken) && jwtProvider.validateAccessToken(
                accessToken)) {
            Claims claims = jwtProvider.getClaimsFromAccessToken(accessToken);
            setAuthenticationToContext(claims);
        }

        filterChain.doFilter(request, response);
    }

    private void setAuthenticationToContext(Claims claims) {
        String userId = claims.getSubject();
        String role = (String) claims.get("role");
        Set<GrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority(role));
        Authentication authentication = new UsernamePasswordAuthenticationToken(userId, "",
                authorities);

        // 요청 - 응답 스코프까지 살아 있는 시큐리티 세션에 저장한다.
        SecurityContextHolder.getContext()
                             .setAuthentication(authentication);
    }

    private boolean isRefreshApi(HttpServletRequest request) {
        RequestMatcher matcher = new AntPathRequestMatcher("/api/members/reissue",
                HttpMethod.POST.name());
        return matcher.matches(request);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        List<AntPathRequestMatcher> requestMatchers = new ArrayList<>();

        // Refresh API는 헤더에 토큰이 있지만 검사하지 않게 처리.
//        requestMatchers.add(
//                new AntPathRequestMatcher("/api/member/reissue", HttpMethod.POST.name()));
        return requestMatchers.stream()
                              .anyMatch(m -> m.matches(request));
    }

    private String extractToken(HttpServletRequest request) {
        String bearerToken = request.getHeader(AUTHORIZATION_HEADER);
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(HEADER_PREFIX)) {
            return bearerToken.substring(HEADER_PREFIX.length());
        }
        return null;
    }

}
