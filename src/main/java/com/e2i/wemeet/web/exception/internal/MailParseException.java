package com.e2i.wemeet.web.exception.internal;

public class MailParseException extends InternalServerException {
    private static final String MESSAGE = "메일 정보를 분석하는 데 실패했어.. 다시 시도해줘!";
    public MailParseException() {
        super(MESSAGE);
    }
}
