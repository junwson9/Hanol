package com.ssafy.hanol.examination.service;

import com.ssafy.hanol.examination.service.dto.request.ExaminationRegisterRequest;
import com.ssafy.hanol.examination.service.dto.response.ExaminationRegisterResponse;
import com.ssafy.hanol.global.examinationSurvey.ExaminationGenerator;
import com.ssafy.hanol.global.examinationSurvey.ExaminationResultService;
import com.ssafy.hanol.global.examinationSurvey.dto.ExaminationGenerationApiRequest;
import com.ssafy.hanol.global.examinationSurvey.dto.ExaminationGenerationApiResponse;
import com.ssafy.hanol.member.domain.Gender;
import com.ssafy.hanol.member.domain.Member;
import com.ssafy.hanol.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ExaminationService {

    private final ExaminationResultService examinationResultService;
    private final ExaminationGenerator examinationGenerator;
    private final MemberRepository memberRepository;


    @Transactional
    public ExaminationRegisterResponse addExamination(ExaminationRegisterRequest request) {
        // 임시 데이터
        Long memberId = 1L;

        Member member = memberRepository.findById(memberId).orElseThrow(); // TODO 예외처리
        // 성별, 연령대 구하기
        String gender = member.getGender().equals(Gender.MALE) ? "0" : "1";
        int ageRange = getAgeRange(member);

        ExaminationGenerationApiRequest examinationGenerationApiRequest = ExaminationGenerationApiRequest.builder()
                .answer1(request.getAnswer1())
                .answer2(request.getAnswer2())
                .answer3(request.getAnswer3())
                .answer4(request.getAnswer4())
                .answer5(request.getAnswer5())
                .answer6(request.getAnswer6())
                .gender(gender)
                .age(ageRange)
                .build();

        log.info("examinationProduceRequest: {}", examinationGenerationApiRequest);

//        ExaminationGenerationApiResponse examinationGenerationApiResponse = examinationResultService.generateExaminationResult(examinationGenerationApiRequest);
        ExaminationGenerationApiResponse result = examinationGenerator.getExaminationResult(examinationGenerationApiRequest);

        // TODO 문진 데이터 저장

        return new ExaminationRegisterResponse(result.getExaminationResult());
    }

    
    // 연령대 구하기
    private int getAgeRange(Member member) {
        LocalDate currentDate = LocalDate.now();
        Date birthDateOld = member.getBirth();
        if(birthDateOld == null) {
            // TODO 예외처리 : 생일이 없는 경우
        }

        LocalDate birthDate = birthDateOld.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
        int age = Period.between(birthDate, currentDate).getYears();

        if(age < 20) return 10; // 20세 미만은 모두 10대로 설정
        if(age < 30) return 20;
        if(age < 40) return 30;
        if(age < 50) return 40;
        if(age < 60) return 50;
        return 60;              // 60세 이상은 모두 60대로 설정
    }

}
