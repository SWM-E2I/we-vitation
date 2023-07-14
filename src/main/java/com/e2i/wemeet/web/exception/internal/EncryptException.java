package com.e2i.wemeet.web.exception.internal;

public class EncryptException extends InternalServerException {

    private static final String MESSAGE = "암호화 중 문제가 발생했습니다.";

    public EncryptException() {
        super(MESSAGE);
    }
}
