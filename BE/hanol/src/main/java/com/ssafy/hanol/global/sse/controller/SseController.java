package com.ssafy.hanol.global.sse.controller;


import com.ssafy.hanol.global.config.auth.AuthMember;
import com.ssafy.hanol.global.config.auth.AuthenticatedMember;
import com.ssafy.hanol.global.sse.service.SseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/sse")
public class SseController {

    private final SseService sseService;

    @GetMapping
    public SseEmitter createConnection(@AuthenticatedMember AuthMember authMember) {

//        // 관리자 권한 확인
//        List<String> roles = authMember.getRoles();
//        boolean isAdmin = roles.stream().anyMatch(role -> role.equals("ADMIN"));
//
//        if (!isAdmin) {
//            throw new CustomException(DiagnoseErrorCode.FORBIDDEN_ACCESS);
//        }
        return sseService.createSseEmitter(authMember.getId());
    }

    public SseEmitter connect(@AuthenticatedMember AuthMember authMember) {
        SseEmitter sseEmitter = sseService.createSseEmitter(authMember.getId());
        log.info("sse 생성 완료");
        return sseEmitter;
    }

}
