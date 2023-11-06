package com.ssafy.hanol.routine.service.batch;

import com.ssafy.hanol.routine.domain.MemberRoutine;
import com.ssafy.hanol.routine.domain.MemberRoutineLog;
import com.ssafy.hanol.routine.repository.MemberRoutineLogRepository;
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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManagerFactory;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
@Slf4j
@RequiredArgsConstructor
public class RoutineBatchConfiguration {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final EntityManagerFactory entityManagerFactory;
    private final MemberRoutineLogRepository memberRoutineLogRepository;

    private static final String JOB_NAME = "dailyRoutineJob";
    public static final int CHUNK_SIZE = 2;


    @Bean(name = JOB_NAME)
    public Job dailyRoutineJob(Step dailyRoutineStep) {
        log.info("======= DailyRoutineJob 시작");
        return jobBuilderFactory.get(JOB_NAME)
                .start(dailyRoutineStep())
                .build();
    }

    @Bean(name = JOB_NAME + "_step")
    @JobScope
    public Step dailyRoutineStep() {
        log.info("======= DailyRoutineStep 시작");
        return stepBuilderFactory.get(JOB_NAME+"_step")
                .<MemberRoutine, MemberRoutineLog>chunk(CHUNK_SIZE)
                .reader(reader(null))
                .processor(processor())
                .writer(writer())
                .faultTolerant()
                .retry(Exception.class)
                .retryLimit(3)  // Chunk 실패 시 재시도 3회
                .build();
    }

    @Bean(name = JOB_NAME + "_reader")
    @StepScope
    public JpaPagingItemReader<MemberRoutine> reader(@Value("#{jobParameters[createdDate]}") String createdDate) {
        Map<String, Object> params = new HashMap<>();
        params.put("createdDate", createdDate);
        log.info(">>>> 리더 createdDate={}", createdDate);

        return new JpaPagingItemReaderBuilder<MemberRoutine>()
                .name(JOB_NAME+"_reader")
                .entityManagerFactory(entityManagerFactory)
                .pageSize(CHUNK_SIZE)   // 한 번에 읽어올 데이터 수
                .queryString("SELECT mr FROM MemberRoutine mr")
                .parameterValues(params)
                .build();
    }


    public ItemProcessor<MemberRoutine, MemberRoutineLog> processor() {
        log.info("프로세서 시작");
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
        log.info("쓰기 시작");
        return memberRoutineLogs -> {
            memberRoutineLogRepository.saveAll((List<MemberRoutineLog>) memberRoutineLogs);
        };
    }

}
