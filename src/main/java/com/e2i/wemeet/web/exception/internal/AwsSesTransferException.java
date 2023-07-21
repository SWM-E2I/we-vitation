package com.e2i.wemeet.web.exception.internal;

public class AwsSesTransferException extends InternalServerException {
    private static final String MESSAGE = "인증 메일을 발송하는 데 실패했어.. 다시 시도해줘!";
    public AwsSesTransferException() {
        super(MESSAGE);
    }
}
