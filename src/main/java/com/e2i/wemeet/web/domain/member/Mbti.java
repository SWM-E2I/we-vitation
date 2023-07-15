package com.e2i.wemeet.web.domain.member;

import com.e2i.wemeet.web.exception.badrequest.InvalidGenderException;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.util.Arrays;

public enum Mbti {

    ESTJ, ESTP, ESFJ, ESFP, ENTJ, ENTP, ENFJ, ENFP,
    ISTJ, ISTP, ISFJ, ISFP, INTJ, INTP, INFJ, INFP,
    NOTHING;

    @JsonCreator
    public static Mbti findBy(String value) {

        return Arrays.stream(Mbti.values())
            .filter(mbti -> mbti.name().equals(value.toUpperCase()))
            .findFirst()
            .orElseThrow(InvalidGenderException::new);
    }
}
