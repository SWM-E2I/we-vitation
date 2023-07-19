package com.e2i.wemeet.web.exception.notfound;

public class MemberNotFoundException extends NotFoundException {
    private static final String MESSAGE = "유저를 찾을 수 없습니다.";

    public MemberNotFoundException() {
        super(MESSAGE);
    }
}
