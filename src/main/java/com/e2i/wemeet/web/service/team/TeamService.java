package com.e2i.wemeet.web.service.team;

import com.e2i.wemeet.web.domain.member.Gender;
import com.e2i.wemeet.web.domain.member.Member;
import com.e2i.wemeet.web.domain.member.MemberRepository;
import com.e2i.wemeet.web.domain.team.Team;
import com.e2i.wemeet.web.domain.team.TeamRepository;
import com.e2i.wemeet.web.exception.badrequest.TeamMemberCountFullException;
import com.e2i.wemeet.web.exception.notfound.MemberNotFoundException;
import com.e2i.wemeet.web.exception.notfound.TeamCodeNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class TeamService {

    private final TeamRepository teamRepository;
    private final MemberRepository memberRepository;

    @Transactional(readOnly = true)
    public String getTeamLeaderName(final String teamCode) {
        return teamRepository.findByTeamCode(teamCode)
            .orElseThrow(TeamCodeNotFoundException::new)
            .getTeamLeader()
            .getNickname();
    }

    public boolean setMemberByPhoneNumberIfExist(final String teamCode, final String phoneNumber) {
        Member member = memberRepository.findByPhoneNumber(phoneNumber).orElse(null);
        if (member == null) {
            return false;
        }

        Team team = teamRepository.findByTeamCode(teamCode)
            .orElseThrow(TeamCodeNotFoundException::new);

        verifyTeamMemberCount(team);
        member.setTeam(team);
        return true;
    }

    public void registerTeam(final Long memberId, final String teamCode) {
        Team team = teamRepository.findByTeamCode(teamCode)
            .orElseThrow(TeamCodeNotFoundException::new);
        Member member = memberRepository.findById(memberId)
            .orElseThrow(MemberNotFoundException::new);

        member.setTeam(team);
    }

    @Transactional(readOnly = true)
    public Gender getTeamGender(final String teamCode) {
        Team team = teamRepository.findByTeamCode(teamCode)
            .orElseThrow(TeamCodeNotFoundException::new);

        return team.getGender();
    }

    private void verifyTeamMemberCount(Team team) {
        int teamMemberCount = memberRepository.countByTeam(team);
        if (team.getMemberCount() <= teamMemberCount) {
            throw new TeamMemberCountFullException();
        }
    }

}
