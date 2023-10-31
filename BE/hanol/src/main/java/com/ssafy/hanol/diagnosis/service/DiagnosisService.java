package com.ssafy.hanol.diagnosis.service;

import com.ssafy.hanol.common.util.s3.ImageUploadUtil;
import com.ssafy.hanol.diagnosis.domain.Diagnosis;
import com.ssafy.hanol.diagnosis.repository.DiagnosisRepository;
import com.ssafy.hanol.diagnosis.service.dto.response.DiagnosisDetailResponse;
import com.ssafy.hanol.diagnosis.service.dto.response.DiagnosisListResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class DiagnosisService {

    private final DiagnosisRepository diagnosisRepository;
    private final ImageUploadUtil imageUploadUtil;

    public DiagnosisDetailResponse findDiagnosis(Long diagnosisId) {
        // TODO 예외처리: 존재하지 않는 diagnosis, 본인 diagnosis가 아님
        Diagnosis diagnosis = diagnosisRepository.findById(diagnosisId).orElseThrow();
        return DiagnosisDetailResponse.from(DiagnosisInfo.from(diagnosis));
    }

    public DiagnosisListResponse findDiagnoses(Integer limit) {
        // 임시 데이터
        Long memberId = 1L;

        Boolean applyLimit = false;
        if(limit != null) {
            applyLimit = true;
        }

        List<DiagnosisInfo> diagnosisInfoList = diagnosisRepository.findDiagnoses(memberId, applyLimit, limit);
        return DiagnosisListResponse.builder()
                .diagnosisInfoList(diagnosisInfoList)
                .build();
    }
    // TODO: 진단하기 -> AI 서버 통신(RabbitMQ) -> 이벤트 등록
    //
    //
    //
    //  진단 결과 listen -> 이미지 업로드, 데이터 저장 -> 결과 return

    

}
