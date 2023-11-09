package com.ssafy.hanol.member.repository;

import com.ssafy.hanol.member.domain.Member;
import com.ssafy.hanol.member.domain.OauthId;
import com.ssafy.hanol.member.domain.OauthProvider;

import java.util.Optional;

public interface MemberRepository {

    Optional<Member> findById(Long id);
    Optional<Member> findByOauthIdAndProvider(OauthId oAuthId, OauthProvider oAuthProvider);
    boolean exisitByOauthIdAndProvider(OauthId oAuthId, OauthProvider oAuthProvider);
    Member save(Member member);
    Optional<Member> findByEmail(String email);
}
