package com.ssafy.hanol.examination.execption;


import com.ssafy.hanol.common.exception.ErrorCode;

public enum ExaminationErrorCode implements ErrorCode {

    NOT_FOUND_EXAMINATION("문진 결과가 없습니다","EXM_001",404);

    private final String message;
    private final String errorCode;
    private final int statusCode;


    ExaminationErrorCode(String message, String errorCode, int statusCode) {
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
