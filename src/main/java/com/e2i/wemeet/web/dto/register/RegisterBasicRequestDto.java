package com.e2i.wemeet.web.dto.register;

import com.e2i.wemeet.web.domain.member.Gender;
import com.e2i.wemeet.web.util.validator.bean.AdmissionYear;
import com.e2i.wemeet.web.util.validator.bean.College;
import com.e2i.wemeet.web.util.validator.bean.CollegeType;
import org.hibernate.validator.constraints.Length;

public record RegisterBasicRequestDto (
    @Length(min = 2, max = 20)
    String nickname,

    Gender gender,

    @College
    String college,

    @CollegeType
    String collegeType,

    @AdmissionYear
    String admissionYear
) {
}
