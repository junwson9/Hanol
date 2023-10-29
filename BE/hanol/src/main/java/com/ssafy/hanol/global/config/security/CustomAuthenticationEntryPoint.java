package com.ssafy.hanol.global.config.security;

import com.ssafy.hanol.common.exception.ErrorCode;
import com.ssafy.hanol.common.response.ResponseFactory;
import com.ssafy.hanol.member.exception.AuthenticationErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;



@Slf4j
@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
            AuthenticationException authException) throws IOException, ServletException {
        ErrorCode errorCode = AuthenticationErrorCode.REQUIRE_LOGIN;
        log.warn("authentication fail" + authException.getMessage());
        ResponseFactory.fail(response, authException.getMessage(), errorCode);
    }
}
