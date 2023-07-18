package com.e2i.wemeet.web.exception.badrequest;

public class EmailDomainNotMatchException extends InvalidValueException {
    private static final String MESSAGE = "사용자의 학교 이메일 주소와 다릅니다.";

    public EmailDomainNotMatchException() {
        super(MESSAGE);
    }
}
