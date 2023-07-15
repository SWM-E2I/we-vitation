package com.e2i.wemeet.web.exception.badrequest;

import com.e2i.wemeet.web.exception.CustomException;

public class InvalidValueException extends CustomException {

    public InvalidValueException(final String message) {
        super(message);
    }
}
