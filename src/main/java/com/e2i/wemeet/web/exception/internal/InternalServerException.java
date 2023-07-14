package com.e2i.wemeet.web.exception.internal;

import com.e2i.wemeet.web.exception.CustomException;

public class InternalServerException extends CustomException {

    public InternalServerException(String message) {
        super(message);
    }
}
