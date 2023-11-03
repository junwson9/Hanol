package com.ssafy.hanol.examination.service;

import com.ssafy.hanol.common.exception.CustomException;
import com.ssafy.hanol.examination.controller.dto.request.ExaminationSurveyApiRequest;
import com.ssafy.hanol.examination.domain.ExaminationResult;
import com.ssafy.hanol.examination.domain.ExaminationSurvey;
import com.ssafy.hanol.examination.execption.ExaminationErrorCode;
import com.ssafy.hanol.examination.repository.ExaminationRepository;
import com.ssafy.hanol.examination.service.dto.request.ExaminationSurveyRequest;
import com.ssafy.hanol.examination.service.dto.response.ExaminationSurveyResponse;
import com.ssafy.hanol.member.domain.Gender;
import com.ssafy.hanol.member.domain.Member;
import com.ssafy.hanol.member.exception.MemberErrorCode;
import com.ssafy.hanol.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.Period;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class ExaminationService {

    private final ExaminationGenerator examinationGenerator;
    private final MemberRepository memberRepository;
    private final ExaminationRepository examinationRepository;


    public ExaminationSurveyResponse addExamination(ExaminationSurveyApiRequest examinationSurveyApiRequest, Long memberId) {
        Member member = findMemberByMemberId(memberId);
        String gender = member.getGender().equals(Gender.MALE) ? "0" : "1";
        int ageRange = getAgeRange(member);

        // 문진 결과 도출 요청
        ExaminationSurveyRequest request = ExaminationSurveyRequest.fromApiRequest(examinationSurveyApiRequest, gender, ageRange, member);
        ExaminationSurveyResponse response = examinationGenerator.getExaminationResult(request);
        log.info("문진 결과 도착: {}", response);

        // 문진 설문 데이터 저장
        ExaminationSurvey examinationSurvey = request.toExaminationSurvey();
        examinationRepository.saveSurvey(examinationSurvey);

        // 문진 결과 데이터 저장
        ExaminationResult examinationResult = response.toExaminationResult(member, examinationSurvey);
        examinationRepository.saveResult(examinationResult);

        return null; // TODO 반환 타입 정하기
    }


    public void findExamination(Long memberId) {
        Member member = findMemberByMemberId(memberId);
        ExaminationResult examinationResult = examinationRepository.findResultTopByMemberIdOrderByIdDesc(memberId)
                .orElseThrow(() -> new CustomException(ExaminationErrorCode.NOT_FOUND_EXAMINATION));
        // TODO 반환 타입 정하기
    }

    
    // 연령대 구하기
    private int getAgeRange(Member member) {
        LocalDate currentDate = LocalDate.now();
        LocalDate birthDate = member.getBirth();
        if(birthDate == null) {
            // TODO 예외처리 : 생일이 없는 경우
        }

        int age = Period.between(birthDate, currentDate).getYears();
        if(age < 10) return 0;
        if(age < 20) return 10;
        if(age < 30) return 20;
        if(age < 40) return 30;
        if(age < 50) return 40;
        if(age < 60) return 50;
        if(age < 70) return 60;
        if(age < 80) return 70;
        return 80;              // 80세 이상은 모두 80대로 설정
    }


    private Member findMemberByMemberId(Long memberId) {
        return memberRepository.findById(memberId).orElseThrow(() -> new CustomException(MemberErrorCode.NOT_FOUND_MEMBER));
    }

}
