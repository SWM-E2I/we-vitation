package com.e2i.wemeet.web.exception.badrequest;

public class TeamMemberCountFullException extends InvalidValueException {
    private static final String MESSAGE = "팀 인원이 가득 찼습니다.";

    public TeamMemberCountFullException() {
        super(MESSAGE);
    }
}
