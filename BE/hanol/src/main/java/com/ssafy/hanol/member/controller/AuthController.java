package com.ssafy.hanol.member.controller;

import com.ssafy.hanol.common.response.ResponseFactory;
import com.ssafy.hanol.global.config.auth.AuthMember;
import com.ssafy.hanol.global.config.auth.AuthenticatedMember;
import com.ssafy.hanol.member.controller.dto.OauthLoginApiRequest;
import com.ssafy.hanol.member.controller.dto.OauthLoginApiResponse;
import com.ssafy.hanol.member.service.AuthService;
import com.ssafy.hanol.member.service.dto.OauthLoginResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/api/member/oauth")
    public ResponseEntity<?> oauthLogin(@Validated @RequestBody OauthLoginApiRequest request) {
        OauthLoginResponse result = authService.login(request.toApplicationDto());
        return ResponseFactory.success("로그인 성공", OauthLoginApiResponse.from(result));
        //return ResponseFactory.success("로그인 성공");
    }

    /**
     * 컨디션 체크를 위한 테스트 요청
     */
    @GetMapping("/api/member/test")
    public ResponseEntity<?> memberTest() {
        return ResponseFactory.success("테스트 성공");
    }
}
