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
    Long sseId;
    byte[] image;

    @Builder
    public DiagnosisAiRequest(Long sseId, byte[] image) {
        this.sseId = sseId;
        this.image = image;
    }

    public static DiagnosisAiRequest from(Long sseId, MultipartFile file) throws IOException {
        return DiagnosisAiRequest.builder()
                .sseId(sseId)
                .image(file.getBytes())
                .build();
    }

}
