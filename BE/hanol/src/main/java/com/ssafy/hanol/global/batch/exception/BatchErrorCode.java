package com.ssafy.hanol.global.batch.exception;


import com.ssafy.hanol.common.exception.ErrorCode;

public enum BatchErrorCode implements ErrorCode {

    JOB_ALREADY_RUNNING("배치 작업이 이미 실행 중입니다.", "BATCH_001", 409),
    JOB_RESTART_FAILED("배치 작업 재시작에 실패했습니다.", "BATCH_002", 500),
    JOB_INSTANCE_ALREADY_COMPLETE("이미 완료된 배치 작업 인스턴스입니다.", "BATCH_003", 409),
    JOB_PARAMETERS_INVALID("잘못된 배치 작업 파라미터입니다.", "BATCH_004", 400),
    JOB_PROCESSING_ERROR("배치 작업 처리 중 오류가 발생했습니다.", "BATCH_005", 500);;

    private final String message;
    private final String errorCode;
    private final int statusCode;


    BatchErrorCode(String message, String errorCode, int statusCode) {
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
