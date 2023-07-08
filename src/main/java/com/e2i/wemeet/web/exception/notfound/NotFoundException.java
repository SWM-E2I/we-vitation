package com.e2i.wemeet.web.exception.notfound;

import com.e2i.wemeet.web.exception.CustomException;

public class NotFoundException extends CustomException {

    public NotFoundException(String message) {
        super(message);
    }
}
