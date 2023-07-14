package com.e2i.wemeet.web.exception.internal;

public class AwsSnsTransferException extends InternalServerException {
    private static final String MESSAGE = "AWS SNS로 문자를 전송하는데 실패했습니다.";
    public AwsSnsTransferException() {
        super(MESSAGE);
    }
}
