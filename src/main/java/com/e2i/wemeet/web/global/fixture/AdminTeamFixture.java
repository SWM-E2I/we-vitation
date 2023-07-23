package com.e2i.wemeet.web.global.fixture;

import com.e2i.wemeet.web.domain.member.Member;
import com.e2i.wemeet.web.domain.team.Team;
import com.e2i.wemeet.web.domain.team.Team.TeamBuilder;
import lombok.Getter;

@Getter
public enum AdminTeamFixture {
    GENERAL(2, true, "안녕하세욥 잘 부탁드립니다!"),
    MESSY(100, true, "저희랑 같이 재미있게 놀아요!!"),
    ;

    private final int memberCount;
    private final boolean drinkingOption;
    private final String introduction;

    AdminTeamFixture(int memberCount, boolean drinkingOption, String introduction) {
        this.memberCount = memberCount;
        this.drinkingOption = drinkingOption;
        this.introduction = introduction;
    }

    public Team create(final Member teamLeader) {
        Team team = createBuilder(teamLeader)
            .build();
        teamLeader.initTeamFromTeamLeader(team);
        return team;
    }

    private TeamBuilder createBuilder(final Member teamLeader) {
        return Team.builder()
            .memberCount(this.memberCount)
            .drinkingOption(this.drinkingOption)
            .introduction(this.introduction)
            .teamLeader(teamLeader);
    }
}
