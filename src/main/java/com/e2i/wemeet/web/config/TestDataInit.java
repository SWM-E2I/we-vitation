package com.e2i.wemeet.web.config;

import com.e2i.wemeet.web.domain.member.Member;
import com.e2i.wemeet.web.domain.member.MemberRepository;
import com.e2i.wemeet.web.domain.team.Team;
import com.e2i.wemeet.web.domain.team.TeamRepository;
import com.e2i.wemeet.web.global.fixture.AdminMemberFixture;
import com.e2i.wemeet.web.global.fixture.AdminTeamFixture;
import jakarta.annotation.PostConstruct;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Component
public class TestDataInit {

    private final InitData initData;

    @PostConstruct
    public void init() {
        initData.initMembersAndTeam();
    }

    @RequiredArgsConstructor
    @Component
    public static class InitData {

        private final TeamRepository teamRepository;
        private final MemberRepository memberRepository;

        @Transactional
        public void initMembersAndTeam() {
            Member kai = AdminMemberFixture.KAI.create();
            Member seyun = AdminMemberFixture.SEYUN.create();
            Member rim = AdminMemberFixture.RIM.create();

            List<Member> members = List.of(kai, seyun, rim);
            memberRepository.saveAll(members);

            Team kaiTeam = AdminTeamFixture.GENERAL.create(kai);
            Team rimTeam = AdminTeamFixture.MESSY.create(rim);

            seyun.setTeam(kaiTeam);
            teamRepository.saveAll(List.of(kaiTeam, rimTeam));
        }
    }
}
