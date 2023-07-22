package com.e2i.wemeet.web.exception.internal;

public class SerializeException extends InternalServerException {
    private static final String MESSAGE = "Serialize Exception";

    public SerializeException() {
        super(MESSAGE);
    }
}
