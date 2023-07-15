package com.e2i.wemeet.web.domain.member;

import com.e2i.wemeet.web.exception.badrequest.InvalidGenderException;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.util.Arrays;

public enum Gender {

    MALE,
    FEMALE;

    @JsonCreator
    public static Gender findBy(String value) {

        return Arrays.stream(Gender.values())
            .filter(gender -> gender.name().equals(value.toUpperCase()))
            .findFirst()
            .orElseThrow(InvalidGenderException::new);
    }
}
