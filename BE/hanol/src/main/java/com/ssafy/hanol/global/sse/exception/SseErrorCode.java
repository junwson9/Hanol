package com.ssafy.hanol.global.sse.exception;


import com.ssafy.hanol.common.exception.ErrorCode;

public enum SseErrorCode implements ErrorCode {

    SSE_EMITTER_NOT_FOUND("사용자의 SSE Emitter를 찾지 못함","SSE_001",500),
    EXPIRED_SSE_EMITTER("만료된 SSE 연결입니다", "SSE_002", 500);

    private final String message;
    private final String errorCode;
    private final int statusCode;


    SseErrorCode(String message, String errorCode, int statusCode) {
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
