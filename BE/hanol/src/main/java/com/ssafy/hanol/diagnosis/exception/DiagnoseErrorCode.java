package com.ssafy.hanol.diagnosis.exception;


import com.ssafy.hanol.common.exception.ErrorCode;

public enum DiagnoseErrorCode implements ErrorCode {

    FORBIDDEN_ACCESS("관리자 권한이 필요합니다","DIAG_001",403);

    private final String message;
    private final String errorCode;
    private final int statusCode;


    DiagnoseErrorCode(String message, String errorCode, int statusCode) {
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
