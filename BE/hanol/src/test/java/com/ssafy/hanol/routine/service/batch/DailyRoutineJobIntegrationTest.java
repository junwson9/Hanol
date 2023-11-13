package com.ssafy.hanol.routine.service.batch;

import com.ssafy.hanol.TestBatchConfig;
import com.ssafy.hanol.routine.repository.JpaMemberRoutineLogRepository;
import com.ssafy.hanol.routine.repository.JpaMemberRoutineRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.batch.test.context.SpringBatchTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * DailyRoutineJob의 통합 테스트용 클래스
 */

//@Sql({"classpath:data-test.sql"}) // 테스트 수행 시 실행할 SQL 파일
@ExtendWith(SpringExtension.class) // Junit5 이용
@SpringBatchTest // JobLauncherTestUtils, JopRepositoryTestUtils 등 테스트에 필요한 유틸 Bean 등록
@SpringBootTest(classes = {RoutineBatchConfiguration.class, TestBatchConfig.class}) // 통합 테스트 실행 시 사용할 Java 설정. (테스트할 Batch Job, 배치 테스트 환경)
public class DailyRoutineJobIntegrationTest {

    @Autowired
    private JobLauncherTestUtils jobLauncherTestUtils;
    @Autowired
    private JpaMemberRoutineLogRepository jpaMemberRoutineLogRepository;
    @Autowired
    private JpaMemberRoutineRepository jpaMemberRoutineRepository;

    @BeforeEach
    public void clean() {
        jpaMemberRoutineLogRepository.deleteAllInBatch();
    }

    @AfterEach
    public void cleanUp() {
        // 데이터베이스 정리
//        jpaMemberRoutineLogRepository.deleteAllInBatch();
    }

    @Test
    @DisplayName("지정한 날짜의 MemberRoutineLog가 MemberRoutine 개수 만큼 생성되어야 한다")
    void testName() throws Exception {

        // 테스트 전 memberRoutineLog가 비어 있는 지 확인
        // 테스트 후 memberRoutine 개수와 memberRoutineLog 개수가 같은 지 검증 (단, 중간에 memberRoutine을 변경 요청이 없는 환경을 전제로 함)
        long initialCount = jpaMemberRoutineLogRepository.count();
        System.out.println("시작 개수: "+initialCount);
        assertThat(initialCount).isEqualTo(0);

        long goalCount = jpaMemberRoutineRepository.count();
        System.out.println("멤버 루틴 goal count: "+goalCount);

        //when
        Long startTime = System.currentTimeMillis();
        JobExecution jobExecution = jobLauncherTestUtils.launchJob();
        Long endTime = System.currentTimeMillis();
        Long durationTime = endTime - startTime;
        System.out.println("JOB 수행 시간: " + durationTime);
        long afterCount = jpaMemberRoutineLogRepository.count();

        //then
        assertThat(jobExecution.getStatus()).isEqualTo(BatchStatus.COMPLETED);
        assertThat(afterCount).isEqualTo(goalCount);
        System.out.println("after count:"+afterCount);
    }


//    @Test
//    @DisplayName("더미데이터 입력")
//    void insertQuery() throws Exception {
//        //given
//
//        //when
//
//        //then
//    }
}