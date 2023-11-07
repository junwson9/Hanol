package com.ssafy.hanol.routine.service.batch;

import com.ssafy.hanol.common.exception.CustomException;
import com.ssafy.hanol.routine.service.batch.exception.BatchErrorCode;
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
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Slf4j
@Component
@RequiredArgsConstructor
public class RoutineBatchScheduler {

    private final JobLauncher jobLauncher;

    private final Job dailyRoutineJob;

    @Scheduled(cron = "0 0 0 * * ?") // 매일 밤 자정 실행
    public void runDailyRoutineJob() {
        LocalDate currentDate = LocalDate.now();
        String currentDateStr = currentDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        log.info("스케쥴러 currentDateStr: {}", currentDateStr);

        // Job Instance를 식별할 파라미터로 requestDate 사용
        JobParameters jobParameters = new JobParametersBuilder()
                .addString("requestDate", currentDateStr)
                .toJobParameters();

        try {
            jobLauncher.run(dailyRoutineJob, jobParameters);

        } catch (JobExecutionAlreadyRunningException e) {
            throw new CustomException(BatchErrorCode.JOB_ALREADY_RUNNING);
        } catch (JobRestartException e) {
            throw new CustomException(BatchErrorCode.JOB_RESTART_FAILED);
        } catch (JobInstanceAlreadyCompleteException e) {
            throw new CustomException(BatchErrorCode.JOB_INSTANCE_ALREADY_COMPLETE);
        } catch (JobParametersInvalidException e) {
            throw new CustomException(BatchErrorCode.JOB_PARAMETERS_INVALID);
        } catch (Exception e) {
            throw new CustomException(BatchErrorCode.JOB_PROCESSING_ERROR);
        }
    }

}
