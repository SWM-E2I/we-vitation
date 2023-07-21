package com.e2i.wemeet.web.exception.internal;

public class EncryptException extends InternalServerException {

    private static final String MESSAGE = "너의 정보를 암호화하는 중에 문제가 발생했어..";

    public EncryptException() {
        super(MESSAGE);
    }
}
