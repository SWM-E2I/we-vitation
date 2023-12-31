package com.e2i.wemeet.web.domain.member;

import com.e2i.wemeet.web.domain.base.BaseTimeEntity;
import com.e2i.wemeet.web.domain.base.CryptoConverter;
import com.e2i.wemeet.web.domain.team.Team;
import com.e2i.wemeet.web.exception.badrequest.GenderNotMatchException;
import com.e2i.wemeet.web.exception.badrequest.TeamMemberCountFullException;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.util.List;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.StringUtils;

@EqualsAndHashCode(callSuper = true)
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "MEMBER")
@Entity
public class Member extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;

    @Column(length = 4, nullable = false)
    private String memberCode;

    @Column(length = 20, nullable = false)
    private String nickname;

    @Column(length = 6, nullable = false)
    @Enumerated(value = EnumType.STRING)
    private Gender gender;

    @Convert(converter = CryptoConverter.class)
    @Column(length = 60, unique = true, nullable = false)
    private String phoneNumber;

    @Embedded
    private CollegeInfo collegeInfo;

    @Embedded
    private Preference preference;

    @Column(length = 7, nullable = false)
    @Enumerated(value = EnumType.STRING)
    private Mbti mbti;

    @Column(length = 100)
    private String introduction;

    @Column(nullable = false)
    private int credit;

    private Boolean imageAuth;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RegistrationType registrationType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "teamId")
    private Team team;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @Builder
    public Member(Long memberId, String memberCode, String nickname, Gender gender,
                  String phoneNumber, CollegeInfo collegeInfo, Preference preference, Mbti mbti,
                  String introduction, int credit, Boolean imageAuth, RegistrationType registrationType,
                  Team team, Role role) {
        this.memberId = memberId;
        this.memberCode = memberCode;
        this.nickname = nickname;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.collegeInfo = collegeInfo;
        this.preference = preference;
        this.mbti = mbti;
        this.introduction = introduction;
        this.credit = credit;
        this.imageAuth = imageAuth;
        this.registrationType = registrationType;
        this.team = team;
        this.role = role;
    }

    public void initTeamFromTeamLeader(final Team team) {
        this.role = Role.MANAGER;
        setTeam(team);
    }

    public void setTeam(final Team team) {
        if (team.getMemberCount() <= team.getMembers().size()) {
            throw new TeamMemberCountFullException();
        }
        if (team.getGender() != this.gender) {
            throw new GenderNotMatchException();
        }

        setTeamField(team);
    }

    private void setTeamField(Team team) {
        this.team = team;

        List<Member> members = team.getMembers();
        if (!members.contains(this)) {
            members.add(this);
        }

        team.activateIfPossible();
    }

    public boolean isEmailAuthenticated() {
        return StringUtils.hasText(this.collegeInfo.getMail());
    }
}

