package com.e2i.wemeet.web.domain.member;

import lombok.Getter;

@Getter
public enum CollegeType {
    NATURAL_SCIENCE("이공계열"),
    LIBERAL_ARTS("인문계열"),
    ART("예체능계열"),
    ETC("기타")
    ;

    private final String collegeTypeName;

    CollegeType(String collegeTypeName) {
        this.collegeTypeName = collegeTypeName;
    }
}
