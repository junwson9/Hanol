package com.ssafy.hanol.routine.exception;


import com.ssafy.hanol.common.exception.ErrorCode;

public enum RoutineErrorCode implements ErrorCode {

    NOT_FOUND_MEMBER_ROUTINE("존재하지 않는 루틴입니다", "RTN_001", 404),
    NOT_FOUNT_ROUTINE_LOG("존재하지 않는 데일리 루틴입니다", "RTN_002", 404),
    FORBIDDEN_ACCESS("접근 권한이 없습니다", "RTN_002", 403);

    private final String message;
    private final String errorCode;
    private final int statusCode;


    RoutineErrorCode(String message, String errorCode, int statusCode) {
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
