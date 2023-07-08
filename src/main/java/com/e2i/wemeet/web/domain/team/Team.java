package com.e2i.wemeet.web.domain.team;

import com.e2i.wemeet.web.domain.BaseTimeEntity;
import com.e2i.wemeet.web.domain.member.Gender;
import com.e2i.wemeet.web.domain.member.Member;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "TEAM")
@Entity
public class Team extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long teamId;

    @Column(nullable = false)
    private int memberCount;

    @Column(nullable = false)
    private String teamCode;

    @Column(length = 6, nullable = false)
    @Enumerated(value = EnumType.STRING)
    private Gender gender;

    @Column(nullable = false)
    private boolean drinkingOption;

    @Column(length = 100)
    private String introduction;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "memberId")
    private Member teamLeader;

    @Builder
    public Team(Long teamId, int memberCount, Gender gender, boolean drinkingOption,
        String introduction, Member teamLeader) {
        this.teamId = teamId;
        this.memberCount = memberCount;
        this.gender = gender;
        this.drinkingOption = drinkingOption;
        this.introduction = introduction;
        this.teamLeader = teamLeader;
    }
}
