package com.e2i.wemeet.web.exception.notfound;

public class TeamCodeNotFoundException extends NotFoundException {

    private static final String MESSAGE = "팀을 찾을 수 없어.. 올바른 정보를 입력해줘!";

    public TeamCodeNotFoundException() {
        super(MESSAGE);
    }
}
