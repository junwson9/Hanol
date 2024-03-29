package com.ssafy.hanol.routine.service.batch;

import com.ssafy.hanol.routine.domain.MemberRoutine;
import com.ssafy.hanol.routine.domain.MemberRoutineLog;
import com.ssafy.hanol.routine.repository.MemberRoutineLogRepository;
import com.ssafy.hanol.routine.repository.MemberRoutineRepository;
import com.ssafy.hanol.global.batch.expression.Expression;
import com.ssafy.hanol.global.batch.item.QueryDslNoOffsetIdPagingItemReader;
import com.ssafy.hanol.global.batch.item.QueryDslNoOffsetPagingItemReader;
import com.ssafy.hanol.global.batch.item.QueryDslPagingItemReader;
import com.ssafy.hanol.global.batch.options.QueryDslNoOffsetNumberOptions;
import io.netty.channel.ConnectTimeoutException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JpaPagingItemReader;
import org.springframework.batch.item.database.builder.JpaPagingItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.DeadlockLoserDataAccessException;
import org.springframework.dao.TransientDataAccessException;
import org.springframework.retry.RetryPolicy;
import org.springframework.retry.backoff.BackOffPolicy;
import org.springframework.retry.backoff.FixedBackOffPolicy;
import org.springframework.retry.policy.SimpleRetryPolicy;

import javax.persistence.EntityManagerFactory;
import java.net.SocketTimeoutException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.ssafy.hanol.routine.domain.QMemberRoutine.memberRoutine;

@Configuration
@Slf4j
@RequiredArgsConstructor
public class RoutineBatchConfiguration {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final EntityManagerFactory entityManagerFactory;
    private final MemberRoutineLogRepository memberRoutineLogRepository;
    private final MemberRoutineRepository memberRoutineRepository;

    private static final String JOB_NAME = "dailyRoutineJob";
    public static final int CHUNK_SIZE = 1000;


    @Bean(name = JOB_NAME)
    public Job dailyRoutineJob() {
        log.info("Starting DailyRoutineJob");
        return jobBuilderFactory.get(JOB_NAME)
                .start(dailyRoutineStep())
                .build();
    }

    @Bean(name = JOB_NAME + "_step")
    @JobScope
    public Step dailyRoutineStep() {
        log.info("Configuring step for {}", JOB_NAME);
        return stepBuilderFactory.get(JOB_NAME+"_step")
                .<MemberRoutine, MemberRoutineLog>chunk(CHUNK_SIZE)
                .reader(reader())
                .processor(processor())
                .writer(writer())
                .faultTolerant()
                .retryPolicy(retryPolicy()) // 해당 예외가 발생하면 Chunk 재시도
                .backOffPolicy(backOffPolicy())
                .build();
    }

    private BackOffPolicy backOffPolicy() {
        FixedBackOffPolicy backOffPolicy = new FixedBackOffPolicy();
        backOffPolicy.setBackOffPeriod(5000); // chunk 실패 시 5초 간격으로 재시도
        log.debug("BackOffPolicy set with a fixed back-off period of 5 seconds");
        return backOffPolicy;
    }

    private RetryPolicy retryPolicy() {
        // retry를 시도할 예외 추가
        Map<Class<? extends Throwable>, Boolean> retryableExceptions = new HashMap<>();
        retryableExceptions.put(ConnectTimeoutException.class, true);
        retryableExceptions.put(DeadlockLoserDataAccessException.class, true);
        retryableExceptions.put(SocketTimeoutException.class, true);
        retryableExceptions.put(TransientDataAccessException.class, true);

        // 위의 예외가 발생하면 최대 3번 retry
        SimpleRetryPolicy retryPolicy = new SimpleRetryPolicy(3, retryableExceptions);
        log.debug("RetryPolicy configured with a limit of 3 attempts for transient errors");
        return retryPolicy;
    }

    @Bean(name = JOB_NAME + "_reader")
    @StepScope
    public QueryDslNoOffsetIdPagingItemReader<MemberRoutine, Long> reader() {
        // No Offset Option 설정
        QueryDslNoOffsetNumberOptions<MemberRoutine, Long> options = new QueryDslNoOffsetNumberOptions<>(memberRoutine.id, Expression.DESC);
        // QueryDsl Reader
        return new QueryDslNoOffsetIdPagingItemReader<>(entityManagerFactory, CHUNK_SIZE, options, jpaQueryFactory ->
                jpaQueryFactory.selectFrom(memberRoutine));
    }

    public ItemProcessor<MemberRoutine, MemberRoutineLog> processor() {
        log.info("Configuring processor for {}", JOB_NAME);
        return memberRoutine -> {
            LocalDate today = LocalDate.now();
            return MemberRoutineLog.builder()
                    .member(memberRoutine.getMember())
                    .routine(memberRoutine.getRoutine())
                    .date(today)
                    .isDone(false)
                    .build();
        };
    }

    public ItemWriter<MemberRoutineLog> writer() {
        log.info("Configuring writer for {}", JOB_NAME);
        return memberRoutineLogs -> {
            memberRoutineLogRepository.saveAll((List<MemberRoutineLog>) memberRoutineLogs);
        };
    }

}
