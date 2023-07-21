package com.e2i.wemeet.web.exception.badrequest;

public class EmailDomainNotMatchException extends InvalidValueException {
    private static final String MESSAGE = "너의 학교 이메일을 입력해줘..";

    public EmailDomainNotMatchException() {
        super(MESSAGE);
    }
}
