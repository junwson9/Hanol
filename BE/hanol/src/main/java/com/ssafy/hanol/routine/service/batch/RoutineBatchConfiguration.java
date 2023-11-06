package com.ssafy.hanol.routine.service.batch;

import com.ssafy.hanol.routine.domain.MemberRoutine;
import com.ssafy.hanol.routine.domain.MemberRoutineLog;
import com.ssafy.hanol.routine.repository.MemberRoutineLogRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JpaPagingItemReader;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManagerFactory;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Configuration
@Slf4j
@RequiredArgsConstructor
public class RoutineBatchConfiguration {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final EntityManagerFactory entityManagerFactory;

    private static final int CHUNK_SIZE = 100;

    @Bean
    public Job dailyRoutineJob(Step dailyRoutineStep) {
        return jobBuilderFactory.get("dailyRoutineJob")
                .start(dailyRoutineStep)
                .build();
    }

//    @Bean
//    public Step dailyRoutineStep() {
//        return stepBuilderFactory.get("dailyRoutineStep")
//                .tasklet(routineTasklet)
//                .build();
//    }

    @Bean
    public Step dailyRoutineStep() {
        return stepBuilderFactory.get("dailyRoutineStep")
                .tasklet(routineTasklet())
                .build();
    }

    @Bean
    public Tasklet routineTasklet() {
        return ((contribution, chunkContext) -> {
            // Tasklet 내부 로직 작성
            log.info(">>> 데일리 루틴 입력 tasklet 수행");
            return RepeatStatus.FINISHED;
        });
    }


    @Bean
    public JpaPagingItemReader<MemberRoutine> memberRoutineJpaPagingItemReader() {
        JpaPagingItemReader<MemberRoutine> reader = new JpaPagingItemReader<>();
        reader.setEntityManagerFactory(entityManagerFactory);
        reader.setPageSize(CHUNK_SIZE); // 한 번에 읽어올 데이터의 수

        reader.setQueryString("SELECT mr FROM MemberRoutine mr");
        return reader;
    }

    @Bean
    public ItemWriter<MemberRoutineLog> memberRoutineLogItemWriter(MemberRoutineLogRepository memberRoutineLogRepository) {
        return items -> {
            List<MemberRoutineLog> routineLogs = new ArrayList<>();
            LocalDate today = LocalDate.now();

            for (MemberRoutineLog memberRoutine : items) {
                MemberRoutineLog routineLog = MemberRoutineLog.builder()
                        .member(memberRoutine.getMember())
                        .routine(memberRoutine.getRoutine())
                        .date(today)
                        .isDone(false)
                        .build();
                routineLogs.add(routineLog);
            }
            memberRoutineLogRepository.saveAll(routineLogs);
        };
    }

}
