package com.ssafy.hanol.routine.service.batch;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Slf4j
@Getter
@NoArgsConstructor
public class CreatedDateJobParameter {
    
    private LocalDate createdDate;

    @Value("#{jobParameters[createdDate]}")  // LocalDate는 jobParmeter로 사용이 불가하므로 String으로 변환하여 사용
    public void setCreateDate(String createdDate) {
        log.info("createdDate: {}", createdDate);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        this.createdDate = LocalDate.parse(createdDate, formatter);
    }
}
