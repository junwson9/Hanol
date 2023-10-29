package com.ssafy.hanol.common.util;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 * Scheduling 작업 상태를 Redis에 업데이트 하는 Util
 */
@Component
@Slf4j
@RequiredArgsConstructor
public class SchedulingStatusUtil {

    private final RedisTemplate<String, String> redisStringTemplate;
    private static final String SCHEDULING_TEMPLATE = "scheduling_status:%s";

    public void startScheduling(String schedulingName) {
        String key = String.format(SCHEDULING_TEMPLATE, schedulingName);
        redisStringTemplate.opsForValue().set(key, "STARTED");
    }

    public void finishedScheduling(String schedulingName) {
        String key = String.format(SCHEDULING_TEMPLATE, schedulingName);
        redisStringTemplate.opsForValue().set(key, "FINISHED");
    }
}
