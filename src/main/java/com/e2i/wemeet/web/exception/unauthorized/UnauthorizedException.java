package com.e2i.wemeet.web.exception.unauthorized;

import com.e2i.wemeet.web.exception.CustomException;

public class UnauthorizedException extends CustomException {

    public UnauthorizedException(final String message) {
        super(message);
    }
}
