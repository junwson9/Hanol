package com.ssafy.hanol.routine.repository;

import com.ssafy.hanol.routine.domain.MemberRoutineLog;
import com.ssafy.hanol.routine.service.batch.RoutineBatchConfiguration;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class JdbcMemberRoutineLogRepository {

    private final JdbcTemplate jdbcTemplate;

    private static final int BATCH_SIZE = RoutineBatchConfiguration.CHUNK_SIZE;

    public void saveAll(List<MemberRoutineLog> memberRoutineLogs) {

        String sql = "INSERT INTO member_routine_log "
                + "(member_id, routine_id, date, is_done) VALUES (?, ?, ?, ?)";

        List<Object[]> batchArgs = new ArrayList<>();

        for (MemberRoutineLog memberRoutineLog : memberRoutineLogs) {
            Object[] values = new Object[]{
                    memberRoutineLog.getMember().getId(),
                    memberRoutineLog.getRoutine().getId(),
                    memberRoutineLog.getDate(),
                    memberRoutineLog.getIsDone() ? 1 : 0
            };
            batchArgs.add(values);

            // 데이터가 많은 경우 BATCH_SIZE 단위로 나눠서 INSERT
            if(batchArgs.size() == BATCH_SIZE) {
                jdbcTemplate.batchUpdate(sql, batchArgs);
                batchArgs.clear();
            }
        }

        if(!batchArgs.isEmpty()) {
            jdbcTemplate.batchUpdate(sql, batchArgs);
        }
    }
}
