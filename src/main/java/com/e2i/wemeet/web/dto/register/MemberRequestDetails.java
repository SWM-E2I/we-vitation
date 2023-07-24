package com.e2i.wemeet.web.dto.register;

import com.e2i.wemeet.web.domain.member.CollegeInfo;
import com.e2i.wemeet.web.domain.member.Gender;
import com.e2i.wemeet.web.domain.member.Mbti;
import com.e2i.wemeet.web.domain.member.Member;
import com.e2i.wemeet.web.domain.member.Preference;
import com.e2i.wemeet.web.domain.member.RegistrationType;
import com.e2i.wemeet.web.domain.member.Role;
import com.e2i.wemeet.web.util.code.RandomCodeUtils;
import java.io.Serializable;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class MemberRequestDetails implements Serializable {
    private static final long serialVersionUID = 77L;

    private String nickname;
    private Gender gender;
    private String phoneNumber;
    private String colleges;
    private String collegeType;
    private String admissionYear;
    private Mbti mbti;
    private String introduction;

    public void setBasicInformation(final RegisterBasicRequestDto requestDto, final String phoneNumber) {
        this.nickname = requestDto.nickname();
        this.gender = requestDto.gender();
        this.phoneNumber = phoneNumber;
        this.colleges = requestDto.college();
        this.collegeType = requestDto.collegeType();
        this.admissionYear = requestDto.admissionYear();
    }

    public void setAdditionalInformation(final RegisterAdditionalRequestDto requestDto) {
        this.mbti = requestDto.mbti();
        this.introduction = requestDto.introduction();
    }

    public Member toEntity() {
        CollegeInfo collegeInfo = toCollegeInfo();

        return Member.builder()
            .memberCode(RandomCodeUtils.createIntegerCode4())
            .nickname(this.nickname)
            .gender(this.gender)
            .phoneNumber(this.phoneNumber)
            .collegeInfo(collegeInfo)
            .mbti(this.mbti)
            .introduction(this.introduction)
            .imageAuth(false)
            .registrationType(RegistrationType.WEB)
            .credit(15)
            .role(Role.USER)
            .build();
    }

    private CollegeInfo toCollegeInfo() {
        return CollegeInfo.builder()
            .college(this.colleges)
            .collegeType(this.collegeType)
            .admissionYear(this.admissionYear)
            .build();
    }

}
