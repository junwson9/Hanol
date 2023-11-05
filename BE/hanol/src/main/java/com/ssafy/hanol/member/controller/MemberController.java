package com.ssafy.hanol.member.controller;

import com.ssafy.hanol.common.response.ResponseFactory;
import com.ssafy.hanol.global.config.auth.AuthMember;
import com.ssafy.hanol.global.config.auth.AuthenticatedMember;
import com.ssafy.hanol.member.controller.dto.MemberInfoApiResponse;
import com.ssafy.hanol.member.controller.dto.MemberSignUpApiRequest;
import com.ssafy.hanol.member.controller.dto.MemberSignUpApiResponse;
import com.ssafy.hanol.member.service.MemberService;
import com.ssafy.hanol.member.service.dto.MemberInfoResponse;
import com.ssafy.hanol.member.service.dto.MemberSignUpResponse;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/members")
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/test2")
    public ResponseEntity<?> test1() {
        return ResponseFactory.success("테스트2 성공");
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signUp(@Validated  @RequestBody MemberSignUpApiRequest memberSignUpApiRequest, @AuthenticatedMember AuthMember authMember) {
        MemberSignUpResponse memberSignUpResponse = memberService.signUp(memberSignUpApiRequest.toMemberSigUpInfo(), authMember.getId());

        return ResponseFactory.success("회원가입 완료", MemberSignUpApiResponse.from(memberSignUpResponse));
    }

    @GetMapping("/info")
    public ResponseEntity<?> info(@AuthenticatedMember AuthMember authMember) {
        MemberInfoResponse memberInfo = memberService.getMemberInfo(authMember.getId());
        return ResponseFactory.success("회원 정보 조회 완료", MemberInfoApiResponse.from(memberInfo));
    }


}
