package com.e2i.wemeet.web.exception.badrequest;

public class CookieNotFoundException extends InvalidValueException {

    private static final String MESSAGE = "Cookie Not Found";

    public CookieNotFoundException() {
        super(MESSAGE);
    }
}
