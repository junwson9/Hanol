package com.ssafy.hanol.member.controller;

import com.ssafy.hanol.common.response.ResponseFactory;
import com.ssafy.hanol.global.config.auth.AuthMember;
import com.ssafy.hanol.global.config.auth.AuthenticatedMember;
import com.ssafy.hanol.member.controller.dto.OauthLoginApiRequest;
import com.ssafy.hanol.member.controller.dto.OauthLoginApiResponse;
import com.ssafy.hanol.member.controller.dto.TokenReissueApiRequest;
import com.ssafy.hanol.member.controller.dto.TokenReissueApiResponse;
import com.ssafy.hanol.member.service.AuthService;
import com.ssafy.hanol.member.service.dto.OauthLoginResponse;
import com.ssafy.hanol.member.service.dto.TokenReissueRequest;
import com.ssafy.hanol.member.service.dto.TokenReissueResponse;
import com.ssafy.hanol.notification.controller.dto.request.FcmTokenApiRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/members")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/oauth")
    public ResponseEntity<?> oauthLogin(@Validated @RequestBody OauthLoginApiRequest request) {
        OauthLoginResponse result = authService.login(request.toApplicationDto());
        return ResponseFactory.success("로그인 성공", OauthLoginApiResponse.from(result));
        //return ResponseFactory.success("로그인 성공");
    }

    @PostMapping("/reissue")
    public ResponseEntity<?> reissueToken(@AuthenticatedMember AuthMember member,
                                          @Validated @RequestBody TokenReissueApiRequest request) {
        TokenReissueResponse result = authService.reissue(new TokenReissueRequest(member.getId(), request.getRefreshToken()));
        return ResponseFactory.success("재발급 성공", new TokenReissueApiResponse(result.getAccessToken().getToken()));
    }

    @PatchMapping("/logout")
    public ResponseEntity<?> logout(@AuthenticatedMember AuthMember authMember,
                                    @RequestBody FcmTokenApiRequest fcmTokenApiRequest) {
        authService.logout(authMember.getId(), fcmTokenApiRequest);
        return ResponseFactory.success("로그아웃 완료");
    }

    /**
     * 컨디션 체크를 위한 테스트 요청
     */
    @GetMapping("/test")
    public ResponseEntity<?> memberTest() {
        return ResponseFactory.success("테스트 성공");
    }


}
