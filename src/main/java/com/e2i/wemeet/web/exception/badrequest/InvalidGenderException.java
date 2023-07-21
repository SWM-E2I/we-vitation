package com.e2i.wemeet.web.exception.badrequest;

public class InvalidGenderException extends InvalidValueException {

    private static final String MESSAGE = "존재하지 않는 성별이야..";

    public InvalidGenderException() {
        super(MESSAGE);
    }
}
