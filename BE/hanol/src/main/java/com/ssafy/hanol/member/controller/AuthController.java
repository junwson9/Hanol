package com.ssafy.hanol.member.controller;

import com.ssafy.hanol.common.response.ResponseFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {

    @PostMapping("/api/member/oauth")
    public ResponseEntity<?> oauthLogin(@Validated @RequestBody OauthLoginApiRequest request) {
        //OauthLoginResponse result = oauthLoginUseCase.login(request.toApplicationDto());
        //return ResponseFactory.success("로그인 성공", OauthLoginApiResponse.from(result));
        return ResponseFactory.success("로그인 성공");
    }
}
