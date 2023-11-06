package com.ssafy.hanol.diagnosis.service.dto.request;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Getter
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class DiagnosisAiRequest {
    Long keyId;
    Long sseId;
    byte[] image;

    @Builder
    public DiagnosisAiRequest(Long keyId, Long sseId, byte[] image) {
        this.keyId = keyId;
        this.sseId = sseId;
        this.image = image;
    }

    public static DiagnosisAiRequest from(Long keyId, Long sseId, MultipartFile file) throws IOException {
        return DiagnosisAiRequest.builder()
                .keyId(keyId)
                .sseId(sseId)
                .image(file.getBytes())
                .build();
    }

}
