package com.ssafy.hanol.routine.service;

import com.ssafy.hanol.member.domain.Member;
import com.ssafy.hanol.routine.domain.MemberRoutine;
import com.ssafy.hanol.routine.domain.MemberRoutineLog;
import com.ssafy.hanol.routine.domain.Routine;
import com.ssafy.hanol.routine.repository.MemberRoutineLogRepository;
import com.ssafy.hanol.routine.repository.MemberRoutineRepository;
import com.ssafy.hanol.routine.repository.RoutineRepository;
import com.ssafy.hanol.routine.service.dto.request.RoutineListModifyRequest;
import com.ssafy.hanol.routine.service.dto.response.RoutineListResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class RoutineService {

    private final RoutineRepository routineRepository;
    private final MemberRoutineRepository memberRoutineRepository;
    private final MemberRoutineLogRepository memberRoutineLogRepository;

    public RoutineListResponse findRoutineList() {
        // 임시 데이터
        Long memberId = 1L;
        List<RoutineListResponse.RoutineItem> suggestedRoutines = new ArrayList<>();
        for(Long i=1L; i<=30L; i=i+5) {
            suggestedRoutines.add(RoutineListResponse.RoutineItem.builder().memberRoutineId(null).routineId(i).routineName("더미데이터"+i).build());
        }

        // 회원이 설정해둔 루틴 리스트 조회
        List<MemberRoutine> memberRoutines = memberRoutineRepository.findByMemberId(memberId);

        List<RoutineListResponse.RoutineItem> myRoutines = memberRoutines.stream()
                .map(RoutineListResponse.RoutineItem::from)
                .collect(Collectors.toList());

//        // TODO Diagnosis 엔티티, 레포지토리 개발 후 구현 가능
//        // 최신 진단 결과 조회
//        Diagnosis latestDiagnosis = diagnosisRepository.findTopByMemberIdByIdDesc(memberId).orElse(null);
//
//        // 진단 결과가 존재하면 value 값들을 리스트로 받기
//        List<Integer> values = new ArrayList<>();
//        if(latestDiagnosis !=  null) {
//            values = latestDiagnosis.getValuesAsList();
//        }
//
//        // QueryDSL 이용
//        // is_default가 true이거나,
//        // values가 !isEmpty() 이고 회원의 value값이 1이상인 경우 is_value값이 true이면서
//        // memberRoutines과 중복되지 않는 루틴 선택
//        List<RoutineListResponse.RoutineItem> suggestedRoutines = routineRepository.findByValuesAndNotMemberRoutines(memberId, values);


        return RoutineListResponse.builder()
                .myRoutines(myRoutines)
                .suggestedRoutines(suggestedRoutines)
                .build();
    }


    public void modifyRoutineList(RoutineListModifyRequest routineListModifyRequest) {
        // 임시 데이터
        Long memberId = 1L;

        // TODO 예외 처리: 스케쥴링 작업 중인 경우, 존재하지 않는 루틴

        List<Long> removedRoutines = routineListModifyRequest.getRemovedRoutines();
        List<Long> addedRoutines = routineListModifyRequest.getAddedRoutines();
        LocalDate today = LocalDate.now();

        if(!removedRoutines.isEmpty()) {
            for(Long routineId : removedRoutines) {
                // 회원별 루틴 테이블에서 삭제
                memberRoutineRepository.deleteByMemberIdAndRoutineId(memberId, routineId);
                // TODO 루틴 이력 테이블에서 삭제

            }
        }

        if(!addedRoutines.isEmpty()) {

            List<Routine> routines = routineRepository.findAllById(addedRoutines);

//            // TODO memberRepository 있어야 주석 해제 가능
//            // 회원별 루틴 테이블에 추가
//            List<MemberRoutine> memberRoutines = routines.stream()
//                    .map(routine -> MemberRoutine.builder()
//                        .member(member)
//                        .routine(routine)
//                        .isNotificationActive(false)
//                        .build())
//                    .collect(Collectors.toList());
//
//            memberRoutineRepository.saveAll(memberRoutines);
//
//
//            // 루틴 이력 테이블에 추가
//            List<MemberRoutineLog> memberRoutineLogs = routines.stream()
//                    .map(routine -> MemberRoutineLog.builder()
//                        .member(member)
//                        .routine(routine)
//                        .date(today)
//                        .isDone(false)
//                        .build())
//                    .collect(Collectors.toList());
//
//            memberRoutineLogRepository.saveAll(memberRoutineLogs);
//
        }

    }


}
