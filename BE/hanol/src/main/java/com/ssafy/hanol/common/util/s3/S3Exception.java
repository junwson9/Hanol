package com.ssafy.hanol.common.util.s3;

import com.ssafy.hanol.common.exception.ErrorCode;

public enum S3Exception implements ErrorCode {
    IMAGE_UPLOAD_FAIL("이미지 업로드 실패", "AWS_001", 500),
    NOT_SUPPORT_IMAGE_EXTENSION("저장할 수 없는 이미지 형식입니다", "AWS_002", 415);

    private final String message;
    private final String errorCode;
    private final int statusCode;


    S3Exception(String message, String errorCode, int statusCode) {
        this.message = message;
        this.errorCode = errorCode;
        this.statusCode = statusCode;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public String getErrorCode() {
        return errorCode;
    }

    @Override
    public int getStatusCode() {
        return statusCode;
    }
}
