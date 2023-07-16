package com.e2i.wemeet.web.dto.register;

import com.e2i.wemeet.web.domain.member.Gender;
import com.e2i.wemeet.web.util.validator.College;
import com.e2i.wemeet.web.util.validator.CollegeType;
import org.hibernate.validator.constraints.Length;

public record RegisterBasicRequestDto (
    @Length(min = 2, max = 20)
    String nickname,

    Gender gender,

    @College
    String college,

    @CollegeType
    String collegeType,

    @Length(min = 2, max = 2)
    String admissionYear
) {
}
