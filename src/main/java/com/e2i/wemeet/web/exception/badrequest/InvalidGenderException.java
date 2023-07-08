package com.e2i.wemeet.web.exception.badrequest;

public class InvalidGenderException extends InvalidValueException {

    private static final String MESSAGE = "성별이 잘못 입력되었습니다.";

    public InvalidGenderException() {
        super(MESSAGE);
    }
}
