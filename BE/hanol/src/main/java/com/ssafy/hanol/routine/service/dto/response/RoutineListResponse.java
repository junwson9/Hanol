package com.ssafy.hanol.routine.service.dto.response;

import com.ssafy.hanol.routine.service.RoutineInfo;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class RoutineListResponse {

    private List<RoutineInfo> myRoutines;
    private List<RoutineInfo> suggestedRoutines;

}
