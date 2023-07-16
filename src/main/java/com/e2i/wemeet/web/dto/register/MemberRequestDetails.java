package com.e2i.wemeet.web.dto.register;

import com.e2i.wemeet.web.domain.member.College;
import com.e2i.wemeet.web.domain.member.CollegeType;
import com.e2i.wemeet.web.domain.member.Gender;
import com.e2i.wemeet.web.domain.member.Mbti;
import java.io.Serializable;
import lombok.ToString;

@ToString
public class MemberRequestDetails implements Serializable {
    private static final long serialVersionUID = 77L;

    private String nickname;
    private Gender gender;
    private College college;
    private CollegeType collegeType;
    private String admissionYear;
    private Mbti mbti;
    private String hobby;
    private String introduction;

    public void setBasicInformation(final RegisterBasicRequestDto requestDto) {
        this.nickname = requestDto.nickname();
        this.gender = requestDto.gender();
        this.college  = requestDto.college();
        this.collegeType = requestDto.collegeType();
        this.admissionYear = requestDto.admissionYear();
    }

    public void setAdditionalInformation(final RegisterAdditionalRequestDto requestDto) {
        this.mbti = requestDto.mbti();
        this.hobby = requestDto.hobby();
        this.introduction = requestDto.introduction();
    }
}
