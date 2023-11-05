package com.ssafy.hanol.diagnosis.service;

import com.ssafy.hanol.common.exception.CustomException;
import com.ssafy.hanol.common.util.s3.ImageUploadUtil;
import com.ssafy.hanol.diagnosis.controller.DiagnosisIdListApiResponse;
import com.ssafy.hanol.diagnosis.controller.dto.response.DiagnosisDetailApiResponse;
import com.ssafy.hanol.diagnosis.domain.Diagnosis;
import com.ssafy.hanol.diagnosis.exception.DiagnoseErrorCode;
import com.ssafy.hanol.diagnosis.repository.DiagnosisRepository;
import com.ssafy.hanol.diagnosis.service.dto.request.DiagnosisAiRequest;
import com.ssafy.hanol.diagnosis.service.dto.request.DiagnosisRequest;
import com.ssafy.hanol.diagnosis.service.dto.response.DiagnosisListResponse;
import com.ssafy.hanol.diagnosis.service.rabbitmq.DiagnosisRequestProducer;
import com.ssafy.hanol.global.sse.service.SseService;
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
    private final DiagnosisRequestProducer diagnosisRequestProducer;
    private final SseService sseService;

    public DiagnosisDetailApiResponse findDiagnosis(Long diagnosisId, Long memberId) {
        Diagnosis diagnosis = null;

        // 최신 데이터 조회
        if (diagnosisId == 0) {
            diagnosis = diagnosisRepository.findTopByMemberIdOrderByIdDesc(memberId).orElse(null);
            if (diagnosis == null) return null; // 진단이 존재하지 않으면 null 반환

        } else { // 특정 id의 데이터 조회
            diagnosis = diagnosisRepository.findById(diagnosisId)
                    .orElseThrow(() -> new CustomException(DiagnoseErrorCode.NOT_FOUND_DIAGNOSIS));
            validateAccessRights(diagnosis, memberId);
        }

        return DiagnosisDetailApiResponse.from(DiagnosisInfo.from(diagnosis));
    }


    public DiagnosisListResponse findDiagnoses(Integer limit, Long memberId) {
        Boolean applyLimit = false;
        if (limit != null) {
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
    public void diagnose(DiagnosisRequest diagnosisRequest) {
        //diagnosisRequestProducer.sendDiagnosisRequest();
        DiagnosisAiRequest diagnosisAiRequest = null;
        try {
            diagnosisAiRequest = DiagnosisAiRequest.from(diagnosisRequest.getMemberId(), diagnosisRequest.getFile());
            diagnosisRequestProducer.sendDiagnosisRequest(diagnosisAiRequest);
        } catch (Exception e) {
            log.error("파일 에러");
            e.printStackTrace();
        }

//        // 임시 로직
//        DiagnoseAiResponse response = DiagnoseAiResponse.builder()
//                .imageUrl("test.com")
//                .value1(1)
//                .value2(1)
//                .value3(1)
//                .value4(1)
//                .value5(1)
//                .value6(1)
//                .build();
//        sseService.sendDiagnosisResult(diagnosisRequest.getMemberId(), response);
    }


    public DiagnosisIdListApiResponse findDiagnosisIds(Long memberId) {
        List<DiagnosisIdInfo> diagnosisIdList = diagnosisRepository.findDiagnosisIds(memberId);
        return DiagnosisIdListApiResponse.from(diagnosisIdList);
    }


    // 본인의 진단 결과인지 검사
    private void validateAccessRights(Diagnosis diagnosis, Long memberId) {
        if (!diagnosis.getMember().getId().equals(memberId)) {
            throw new CustomException(DiagnoseErrorCode.FORBIDDEN_ACCESS);
        }
    }

}
