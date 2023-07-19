package com.e2i.wemeet.web.exception.internal;

public class MailParseException extends InternalServerException {
    private static final String MESSAGE = "메일을 파싱하는데 실패했습니다.";
    public MailParseException() {
        super(MESSAGE);
    }
}
