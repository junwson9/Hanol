package com.ssafy.hanol.routine.service;

import com.ssafy.hanol.member.domain.*;
import com.ssafy.hanol.member.repository.MemberRepository;
import com.ssafy.hanol.routine.domain.MemberRoutine;
import com.ssafy.hanol.routine.domain.Routine;
import com.ssafy.hanol.routine.repository.MemberRoutineRepository;
import com.ssafy.hanol.routine.repository.RoutineRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TestService {

    private final MemberRepository memberRepository;
    private final MemberRoutineRepository memberRoutineRepository;
    private final RoutineRepository routineRepository;


    public void insertMemberDummy(Long startNo, Long endNo) {
        for(Long i=startNo; i<endNo; i++) {
            Member member = Member.builder()
                    .name("test")
                    .email("ssafy@ssafy.com")
                    .gender(Gender.FEMALE)
                    .birth(LocalDate.parse("1999-01-05"))
                    .oauthProvider(OauthProvider.KAKAO)
                    .oauthId(OauthId.of("ssafy"+i))
                    .role(Role.MEMBER)
                    .build();
            memberRepository.save(member);
        }
    }

    public void insertDummy(Long startNo, Long endNo) {
        for(Long i=startNo; i<endNo; i++) {
            Long memberId = i;
            Member member = memberRepository.findById(i).orElseThrow();
            List<MemberRoutine> list = new ArrayList<>();
            for(Long j=1L; j<35; j++) {
                Routine routine = routineRepository.findById(j).orElseThrow();
                MemberRoutine memberRoutine = MemberRoutine.builder()
                        .member(member)
                        .routine(routine)
                        .isNotificationActive(true)
                        .notificationTime(LocalTime.parse("19:00:00"))
                        .build();
                list.add(memberRoutine);
            }
            memberRoutineRepository.saveAll(list);
        }
    }

}
