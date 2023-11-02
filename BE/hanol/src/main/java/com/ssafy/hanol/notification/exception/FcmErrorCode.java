package com.ssafy.hanol.notification.exception;


import com.ssafy.hanol.common.exception.ErrorCode;

public enum FcmErrorCode implements ErrorCode {
    FCM_SEND_FAIL("FCM 푸시 발송 요청을 실패했습니다","FCM_001",500);

    private final String message;
    private final String errorCode;
    private final int statusCode;


    FcmErrorCode(String message, String errorCode, int statusCode) {
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
