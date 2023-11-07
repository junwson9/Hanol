package com.ssafy.hanol;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@EnableBatchProcessing // 배치 기능 활성화
public class HanolApplication {

	public static void main(String[] args) {
		SpringApplication.run(HanolApplication.class, args);
	}

}
