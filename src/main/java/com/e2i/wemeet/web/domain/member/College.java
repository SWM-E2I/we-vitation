package com.e2i.wemeet.web.domain.member;

import lombok.Getter;

@Getter
public enum College {
    KOREA("고려대학교"),
    YONSEI("연세대학교"),
    SNU("서울대학교"),
    HANYANG("한양대학교"),
    SOGANG("서강대학교"),
    EWHA("이화여자대학교"),
    SEOUL_WOMAN("서울여자대학교"),
    ANYANG("안양대학교"),
    ;

    private final String collegeName;

    College(String collegeName) {
        this.collegeName = collegeName;
    }
}
