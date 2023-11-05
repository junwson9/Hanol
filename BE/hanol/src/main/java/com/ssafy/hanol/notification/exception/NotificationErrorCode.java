package com.ssafy.hanol.notification.exception;


import com.ssafy.hanol.common.exception.ErrorCode;

public enum NotificationErrorCode implements ErrorCode {
    NOT_FOUND_NOTIFICATION_CONFIGURATION("알림 설정을 찾을 수 없습니다","NOTI_001",404);

    private final String message;
    private final String errorCode;
    private final int statusCode;


    NotificationErrorCode(String message, String errorCode, int statusCode) {
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
