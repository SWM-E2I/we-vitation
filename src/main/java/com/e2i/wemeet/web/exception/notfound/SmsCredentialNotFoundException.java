package com.e2i.wemeet.web.exception.notfound;

public class SmsCredentialNotFoundException extends NotFoundException {
    private static final String MESSAGE = "인증 번호가 만료되었어.. 인증 번호를 재전송 받아서 다시 인증해줘!";

    public SmsCredentialNotFoundException() {
        super(MESSAGE);
    }

}
