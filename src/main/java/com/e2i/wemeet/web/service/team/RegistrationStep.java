package com.e2i.wemeet.web.service.team;

public enum RegistrationStep {
    BASIC_INFO("기본 정보 입력 단계"),
    EMAIL_AUTHENTICATION("이메일 인증 단계"),
    PROFILE_IMAGE("프로필 이미지 등록 단계"),
    DONE("모든 정보 입력 완료"),

    EXIST("이미 팀이 존재하는 회원"),
    ;

    private final String description;

    RegistrationStep(final String description) {
        this.description = description;
    }
}
