package com.e2i.wemeet.web.dto.register;

import com.e2i.wemeet.web.domain.member.Gender;
import com.e2i.wemeet.web.util.validator.bean.AdmissionYear;
import com.e2i.wemeet.web.util.validator.bean.College;
import com.e2i.wemeet.web.util.validator.bean.CollegeType;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

public record RegisterBasicRequestDto (

    @Length(min = 2, max = 20)
    @NotNull
    String nickname,

    @NotNull
    Gender gender,

    @College
    @NotNull
    String college,

    @CollegeType
    @NotNull
    String collegeType,

    @AdmissionYear
    @NotNull
    String admissionYear
) {
    public static RegisterBasicRequestDto getEmptyInstance() {
        return new RegisterBasicRequestDto("", Gender.MALE, "", "", "");
    }
}
