package com.ssafy.hanol.routine.repository;

import com.ssafy.hanol.routine.domain.MemberRoutine;
import com.ssafy.hanol.routine.domain.MemberRoutineLog;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Repository
@Slf4j
@RequiredArgsConstructor
public class JdbcMemberRoutineRepository {

    private final JdbcTemplate jdbcTemplate;

    private static final int BATCH_SIZE = 50;

    public void saveAll(List<MemberRoutine> memberRoutines) {

        String sql = "INSERT INTO member_routine "
                + "(member_id, routine_id, is_notification_active, notification_time, created_date) VALUES (?, ?, ?, ?, ?)";

        List<Object[]> batchArgs = new ArrayList<>();

        LocalDateTime createdDate = LocalDateTime.now();

        for (MemberRoutine memberRoutine : memberRoutines) {
            Object[] values = new Object[]{
                    memberRoutine.getMember().getId(),
                    memberRoutine.getRoutine().getId(),
                    memberRoutine.getIsNotificationActive() ? 1 : 0,
                    memberRoutine.getNotificationTime(),
                    createdDate
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
