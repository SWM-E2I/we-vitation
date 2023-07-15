package com.e2i.wemeet.web.exception.notfound;

public class SmsCredentialNotFoundException extends NotFoundException {
    private static final String MESSAGE = "해당 번호로 발급된 인증 번호가 없습니다";

    public SmsCredentialNotFoundException() {
        super(MESSAGE);
    }

}
