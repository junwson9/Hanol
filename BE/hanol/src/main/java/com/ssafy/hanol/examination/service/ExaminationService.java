package com.ssafy.hanol.examination.service;

import com.ssafy.hanol.examination.controller.dto.response.ExaminationRegisterApiResponse;
import com.ssafy.hanol.examination.service.dto.request.ExaminationRegisterRequest;
import com.ssafy.hanol.examination.service.dto.response.ExaminationRegisterResponse;
import com.ssafy.hanol.global.examinationAI.ExaminationResultService;
import com.ssafy.hanol.global.examinationAI.dto.ExaminationProduceRequest;
import com.ssafy.hanol.global.examinationAI.dto.ExaminationProduceResponse;
import com.ssafy.hanol.member.domain.Gender;
import com.ssafy.hanol.member.domain.Member;
import com.ssafy.hanol.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class ExaminationService {

    private final ExaminationResultService examinationResultService;
    private final MemberRepository memberRepository;


    @Transactional
    public ExaminationRegisterResponse addExamination(ExaminationRegisterRequest request) {
        // 임시 데이터
        Long memberId = 1L;

        Member member = memberRepository.findById(memberId).orElseThrow(); // TODO 예외처리
        String gender = member.getGender().equals(Gender.MALE) ? "0" : "1";
        int age = 20; // TODO 연령대 계산

        ExaminationProduceRequest examinationProduceRequest = ExaminationProduceRequest.builder()
                .answer1(request.getAnswer1())
                .answer2(request.getAnswer2())
                .answer3(request.getAnswer3())
                .answer4(request.getAnswer4())
                .answer5(request.getAnswer5())
                .answer6(request.getAnswer6())
                .gender(gender)
                .age(age)
                .build();

        ExaminationProduceResponse examinationProduceResponse = examinationResultService.produceExamination(examinationProduceRequest);

        // TODO 문진 데이터 저장

        return new ExaminationRegisterResponse(examinationProduceResponse.getExaminationResult());
    }

}
