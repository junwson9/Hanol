package com.ssafy.hanol.global.config;

import com.amazonaws.services.s3.AmazonS3Client;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class MockAmazonS3ConfigTest extends AmazonS3Config {

    @Bean
    @Override
    public AmazonS3Client amazonS3Client(){
        return Mockito.mock(AmazonS3Client.class);
    }

}