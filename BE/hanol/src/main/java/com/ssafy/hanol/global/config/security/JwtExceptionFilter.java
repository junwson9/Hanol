package com.ssafy.hanol.global.config.security;

import com.ssafy.hanol.common.exception.CommonErrorCode;
import com.ssafy.hanol.common.exception.CustomException;
import com.ssafy.hanol.common.response.ResponseFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtExceptionFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
            FilterChain filterChain) throws ServletException, IOException {
        try{
            filterChain.doFilter(request, response);
        }catch (CustomException e){
            ResponseFactory.fail(response, e.getMessage(), e.getErrorCode());
        }catch (Exception e){
            ResponseFactory.fail(response, e.getMessage(), CommonErrorCode.SERVER_ERROR);
        }
    }

}
