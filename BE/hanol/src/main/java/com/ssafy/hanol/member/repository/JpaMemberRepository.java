package com.ssafy.hanol.member.repository;

import com.ssafy.hanol.member.domain.Member;
import com.ssafy.hanol.member.domain.OauthId;
import com.ssafy.hanol.member.domain.OauthProvider;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JpaMemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByOauthIdAndOauthProvider(OauthId oAuthId, OauthProvider oAuthProvider);
    boolean existsByOauthIdAndOauthProvider(OauthId oAuthId, OauthProvider oAuthProvider);
}
