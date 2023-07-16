package com.e2i.wemeet.web.dto.register;

import com.e2i.wemeet.web.domain.member.College;
import com.e2i.wemeet.web.domain.member.CollegeType;
import com.e2i.wemeet.web.domain.member.Gender;
import org.hibernate.validator.constraints.Length;

public record RegisterBasicRequestDto (
    @Length(min = 2, max = 20)
    String nickname,

    Gender gender,
    College college,
    CollegeType collegeType,

    @Length(min = 2, max = 2)
    String admissionYear
) {
}
