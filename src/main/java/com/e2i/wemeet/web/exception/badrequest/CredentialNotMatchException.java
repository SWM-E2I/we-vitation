package com.e2i.wemeet.web.exception.badrequest;

public class CredentialNotMatchException extends InvalidValueException {

    private static final String MESSAGE = "인증 번호가 일치하지 않습니다";
    public CredentialNotMatchException() {
        super(MESSAGE);
    }
}
