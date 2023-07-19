package com.e2i.wemeet.web.exception.notfound;

public class ProfileImageNotFoundException extends NotFoundException {
    private static final String MESSAGE = "프로필 이미지를 찾을 수 없습니다.";

    public ProfileImageNotFoundException() {
        super(MESSAGE);
    }
}
