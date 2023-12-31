package com.e2i.wemeet.web.global.fixture;

import static com.e2i.wemeet.web.global.fixture.AdminCollegeInfoFixture.ANYANG;
import static com.e2i.wemeet.web.global.fixture.AdminCollegeInfoFixture.KU;
import static com.e2i.wemeet.web.global.fixture.AdminCollegeInfoFixture.SEOUL_WOMAN;
import static com.e2i.wemeet.web.global.fixture.AdminPreferenceFixture.GENERAL_PREFERENCE;

import com.e2i.wemeet.web.domain.member.CollegeInfo;
import com.e2i.wemeet.web.domain.member.Gender;
import com.e2i.wemeet.web.domain.member.Mbti;
import com.e2i.wemeet.web.domain.member.Member;
import com.e2i.wemeet.web.domain.member.Preference;
import com.e2i.wemeet.web.domain.member.RegistrationType;
import com.e2i.wemeet.web.domain.member.Role;
import java.util.Arrays;

public enum AdminMemberFixture {
    KAI("4100", "kai", Gender.MALE, "+821012341234",
        ANYANG.create(), GENERAL_PREFERENCE.create(), Mbti.INFJ,
        "hi", 100, Role.USER),
    RIM("4101", "rim", Gender.FEMALE, "+821088990011",
        SEOUL_WOMAN.create(), GENERAL_PREFERENCE.create(), Mbti.ISTP,
        "hello", 100, Role.MANAGER),
    SEYUN("4102", "seyun", Gender.MALE, "+821033445566",
        KU.create(), GENERAL_PREFERENCE.create(), Mbti.ESFJ,
        "hey", 100, Role.USER)
    ;

    private final String memberCode;
    private final String nickname;
    private final Gender gender;
    private final String phoneNumber;
    private final CollegeInfo collegeInfo;
    private final Preference preference;
    private final Mbti mbti;
    private final String introduction;
    private final int credit;
    private final Role role;

    AdminMemberFixture(String memberCode, String nickname, Gender gender, String phoneNumber,
        CollegeInfo collegeInfo, Preference preference, Mbti mbti, String introduction, int credit, Role role) {
        this.memberCode = memberCode;
        this.nickname = nickname;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.collegeInfo = collegeInfo;
        this.preference = preference;
        this.mbti = mbti;
        this.introduction = introduction;
        this.credit = credit;
        this.role = role;
    }

    public Member create() {
        return createBuilder()
            .build();
    }

    public Member createWithCredit(final int credit) {
        return createBuilder()
            .credit(credit)
            .build();
    }

    public Member createWithRole(final Role role) {
        return createBuilder()
            .role(role)
            .build();
    }

    public Member createWithRoleCredit(final Role role, final int credit) {
        return createBuilder()
            .role(role)
            .credit(credit)
            .build();
    }

    private Member.MemberBuilder createBuilder() {
        return Member.builder()
            .memberCode(this.memberCode)
            .nickname(this.nickname)
            .gender(this.gender)
            .phoneNumber(this.phoneNumber)
            .collegeInfo(this.collegeInfo)
            .preference(this.preference)
            .mbti(this.mbti)
            .introduction(this.introduction)
            .registrationType(RegistrationType.WEB)
            .credit(this.credit)
            .role(this.role);
    }

    public static AdminMemberFixture getFixture(final String fixture) {
        return Arrays.stream(AdminMemberFixture.values())
            .filter(memberFixture -> memberFixture.name().equals(fixture))
            .findFirst().orElseThrow();
    }
}
