package com.e2i.wemeet.web.exception.internal;

public class DeserializeException extends InternalServerException {
    private static final String MESSAGE = "Deserialize Exception";

    public DeserializeException() {
        super(MESSAGE);
    }
}
