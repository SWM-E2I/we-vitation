package com.e2i.wemeet.web.exception;

import lombok.Getter;

@Getter
public class CustomException extends RuntimeException {

    public CustomException(final String message) {
        super(message);
    }
}
