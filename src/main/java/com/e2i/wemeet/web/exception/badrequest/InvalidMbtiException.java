package com.e2i.wemeet.web.exception.badrequest;

public class InvalidMbtiException extends InvalidValueException {

    private static final String MESSAGE = "존재하지 않는 MBTI야..";

    public InvalidMbtiException() {
        super(MESSAGE);
    }
}
