package com.e2i.wemeet.web.exception.internal;

public class AwsSnsTransferException extends InternalServerException {
    private static final String MESSAGE = "인증 번호를 발송하는 데 문제가 발생했어.. 다시 시도해줘!";
    public AwsSnsTransferException() {
        super(MESSAGE);
    }
}
