package com.e2i.wemeet.web.domain.member;

import lombok.Getter;

@Getter
public enum Colleges {
    KOREA("고려대학교", "korea.ac.kr"),
    YONSEI("연세대학교", "yonsei.ac.kr"),
    SNU("서울대학교", "snu.ac.kr"),
    HANYANG("한양대학교", "hanyang.ac.kr"),
    SOGANG("서강대학교", "sogang.ac.kr"),
    EWHA("이화여자대학교", "ewha.ac.kr"),
    SEOUL_WOMAN("서울여자대학교", "swu.ac.kr"),
    ANYANG("안양대학교", "anyang.ac.kr"),
    ;

    private final String collegeName;
    private final String emailDomain;

    Colleges(String collegeName, String emailDomain) {
        this.collegeName = collegeName;
        this.emailDomain = emailDomain;
    }
}
