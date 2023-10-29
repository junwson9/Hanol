package com.ssafy.hanol;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class HanolApplication {

	public static void main(String[] args) {
		SpringApplication.run(HanolApplication.class, args);
	}

}
