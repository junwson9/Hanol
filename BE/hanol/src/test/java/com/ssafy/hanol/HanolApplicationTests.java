package com.ssafy.hanol;

import com.amazonaws.services.s3.AmazonS3Client;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.junit.jupiter.Testcontainers;

@SpringBootTest
@Testcontainers
@ActiveProfiles("test")
class HanolApplicationTests {

    @MockBean
    private AmazonS3Client amazonS3Client;

}
