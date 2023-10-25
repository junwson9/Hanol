package com.ssafy.hanol.global.config.auth;

import com.ssafy.hanol.common.exception.CustomException;
import com.ssafy.hanol.member.exception.AuthenticationErrorCode;
import org.springframework.core.MethodParameter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

public class AuthenticatedMemberArgumentResolver  implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter parameter) {

        boolean isAuthenticatedMemberAnnotation = parameter.getParameterAnnotation(AuthenticatedMember.class) != null;
        boolean isAuthMemberClass = AuthMember.class.equals(parameter.getParameterType());
        return isAuthenticatedMemberAnnotation && isAuthMemberClass;
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
            NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        Authentication authentication = SecurityContextHolder.getContext()
                                                             .getAuthentication();

        if(authentication == null || authentication.getPrincipal() == null){
            throw new CustomException(AuthenticationErrorCode.REQUIRE_LOGIN);
        }

        Long memberId = Long.valueOf(authentication.getPrincipal()
                                                 .toString());
        return new AuthMember(memberId);
    }
}
