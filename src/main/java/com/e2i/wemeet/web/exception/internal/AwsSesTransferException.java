package com.e2i.wemeet.web.exception.internal;

public class AwsSesTransferException extends InternalServerException {
    private static final String MESSAGE = "인증 메일을 전송하는데 실패했습니다.";
    public AwsSesTransferException() {
        super(MESSAGE);
    }
}
