package com.e2i.wemeet.web.exception.notfound;

public class EmailCredentialNotFoundException extends NotFoundException {
    private static final String MESSAGE = "인증 번호가 만료되었습니다. 다시 인증해주세요";

    public EmailCredentialNotFoundException() {
        super(MESSAGE);
    }
}
