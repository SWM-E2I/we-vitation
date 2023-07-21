package com.e2i.wemeet.web.exception.badrequest;

public class TeamMemberCountFullException extends InvalidValueException {
    private static final String MESSAGE = "팀 정원이 이미 꽉 찼어..";

    public TeamMemberCountFullException() {
        super(MESSAGE);
    }
}
