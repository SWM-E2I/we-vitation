package com.e2i.wemeet.web.exception.badrequest;

public class CookieNotFoundException extends InvalidValueException {

    private static final String MESSAGE = "이전 요청 값을 찾을 수 없어..";

    public CookieNotFoundException() {
        super(MESSAGE);
    }
}
