package com.ssafy.hanol.routine.service.batch;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Slf4j
@Service
@RequiredArgsConstructor
public class RoutineBatchScheduler {

    private final JobLauncher jobLauncher;

    private final Job dailyRoutineJob;

    @Scheduled(cron = "0 42 2 * * ?") // 매일 밤 자정 실행
    public void runDailyRoutineJob() {
        LocalDate currentDate = LocalDate.now();
        String currentDateStr = currentDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        log.info("스케쥴러 currentDateStr: {}", currentDateStr);

        JobParameters jobParameters = new JobParametersBuilder()
                .addString("createdDate", currentDateStr)
                .toJobParameters();

        try {
            jobLauncher.run(dailyRoutineJob, jobParameters);

        } catch (JobExecutionAlreadyRunningException e) {
            throw new RuntimeException(e);
        } catch (JobRestartException e) {
            throw new RuntimeException(e);
        } catch (JobInstanceAlreadyCompleteException e) {
            throw new RuntimeException(e);
        } catch (JobParametersInvalidException e) {
            throw new RuntimeException(e);
        }
    }

}
