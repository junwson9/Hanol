package com.ssafy.hanol.member.repository;

import com.ssafy.hanol.member.domain.Member;
import com.ssafy.hanol.member.domain.OauthId;
import com.ssafy.hanol.member.domain.OauthProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;


@Component
@RequiredArgsConstructor
public class MemberRepositoryImpl implements MemberRepository {

    private final JpaMemberRepository jpaMemberRepository;

    @Override
    public Optional<Member> findById(Long id) {
        return jpaMemberRepository.findById(id);
    }

    @Override
    public Optional<Member> findByOauthIdAndProvider(OauthId oAuthId, OauthProvider oAuthProvider) {
        return jpaMemberRepository.findByOauthIdAndOauthProvider(oAuthId, oAuthProvider);
    }

    @Override
    public boolean exisitByOauthIdAndProvider(OauthId oAuthId, OauthProvider oAuthProvider) {
        return jpaMemberRepository.existsByOauthIdAndOauthProvider(oAuthId, oAuthProvider);
    }

    @Override
    public Member save(Member member) {
        return jpaMemberRepository.save(member);
    }

    @Override
    public Optional<Member> findByEmail(String email) {
        return jpaMemberRepository.findByEmail(email);
    }
}
