package com.ssafy.hanol.routine.service.dto.response;

import com.ssafy.hanol.routine.service.RoutineLogInfo;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class RoutineLogListResponse {

    private List<RoutineLogInfo> dailyRoutines;

}
