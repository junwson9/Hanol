package com.ssafy.hanol.member.service;

import com.ssafy.hanol.common.exception.CustomException;
import com.ssafy.hanol.member.domain.Member;
import com.ssafy.hanol.member.domain.Role;
import com.ssafy.hanol.member.domain.Token;
import com.ssafy.hanol.member.exception.AuthenticationErrorCode;
import com.ssafy.hanol.member.exception.MemberErrorCode;
import com.ssafy.hanol.member.repository.MemberRepository;
import com.ssafy.hanol.member.service.dto.MemberInfoResponse;
import com.ssafy.hanol.member.service.dto.MemberSignUpRequest;
import com.ssafy.hanol.member.service.dto.MemberSignUpResponse;
import com.ssafy.hanol.member.service.token.TokenService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class MemberService {

    private final MemberRepository memberRepository;
    private final TokenService tokenService;

    public MemberSignUpResponse signUp(MemberSignUpRequest memberSignUpRequest, Long memberId) {
        Member member = findMemberByMemberId(memberId);
        member.updateMemberGenderAndBirth(memberSignUpRequest);
        member.updateMemberRole(Role.MEMBER);

        memberRepository.save(member);

        tokenService.getRefreshTokenFromMember(member).orElseThrow(() -> new CustomException(AuthenticationErrorCode.REQUIRE_LOGIN));

        Token accessToken = tokenService.createAccessToken(member);

        return new MemberSignUpResponse(accessToken);
    }

    public MemberInfoResponse getMemberInfo(Long memberId) {
        Member member = findMemberByMemberId(memberId);

        return MemberInfoResponse.from(member);
    }

    private Member findMemberByMemberId(Long memberId) {
        return memberRepository.findById(memberId).orElseThrow(() -> new CustomException(MemberErrorCode.NOT_FOUND_MEMBER));
    }
}
