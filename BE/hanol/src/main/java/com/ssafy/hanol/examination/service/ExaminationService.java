package com.ssafy.hanol.examination.service;

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
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;

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

        // 성별 구하기
        String gender = member.getGender().equals(Gender.MALE) ? "0" : "1";

        // 연령대 구하기
        LocalDate currentDate = LocalDate.now();
        LocalDate birthDate = member.getBirth().toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
        int age = Period.between(birthDate, currentDate).getYears();
        int ageRange;
        if(age < 30) {
            ageRange = 20;  // 30세 미만은 모두 20대로 설정
        } else if (age < 40) {
            ageRange = 30;
        } else if (age < 50) {
            ageRange = 40;
        } else if (age < 60){
            ageRange = 50;
        } else {
            ageRange = 60;  // 60세 이상은 모두 50대로 설정
        }

        ExaminationProduceRequest examinationProduceRequest = ExaminationProduceRequest.builder()
                .answer1(request.getAnswer1())
                .answer2(request.getAnswer2())
                .answer3(request.getAnswer3())
                .answer4(request.getAnswer4())
                .answer5(request.getAnswer5())
                .answer6(request.getAnswer6())
                .gender(gender)
                .age(ageRange)
                .build();

        log.info("examinationProduceRequest: {}", examinationProduceRequest);

        ExaminationProduceResponse examinationProduceResponse = examinationResultService.produceExamination(examinationProduceRequest);

        // TODO 문진 데이터 저장

        return new ExaminationRegisterResponse(examinationProduceResponse.getExaminationResult());
    }

}
