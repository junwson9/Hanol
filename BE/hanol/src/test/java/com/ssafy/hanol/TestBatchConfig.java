package com.ssafy.hanol;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * 배치 테스트 환경을 설정하는 클래스
 * 모든 테스트 클래스에 설정하는 불필요함을 없애기 위해 사용
 */

@Configuration
@EnableAutoConfiguration
@EnableBatchProcessing
@ComponentScan(basePackages = "com.ssafy.hanol")
public class TestBatchConfig {

}
