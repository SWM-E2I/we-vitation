package com.e2i.wemeet.web.exception.badrequest;

import com.e2i.wemeet.web.exception.CustomException;

public class GenderNotMatchException extends CustomException {
    private static final String MESSAGE = "Gender is not match MEMBER -> TEAM";

    public GenderNotMatchException() {
        super(MESSAGE);
    }
}
