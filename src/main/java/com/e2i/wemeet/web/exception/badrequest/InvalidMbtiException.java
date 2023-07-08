package com.e2i.wemeet.web.exception.badrequest;

public class InvalidMbtiException extends InvalidValueException {

    private static final String MESSAGE = "MBTI가 잘못 입력되었습니다.";

    public InvalidMbtiException() {
        super(MESSAGE);
    }
}
