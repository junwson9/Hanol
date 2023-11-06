package com.ssafy.hanol.diagnosis.exception;


import com.ssafy.hanol.common.exception.ErrorCode;

public enum DiagnoseErrorCode implements ErrorCode {

    ADMIN_REQUIRED("관리자 권한이 필요합니다","DIAG_001",403),
    NOT_FOUND_DIAGNOSIS("진단 결과를 찾을 수 없습니다", "DIAG_002", 404),
    FORBIDDEN_ACCESS("접근 권한이 없습니다", "DIAG_003", 403),
    FILE_CONVERSION_ERROR("이미지 파일 변환 중 에러가 발생했습니다", "DIAG_004", 500);

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
