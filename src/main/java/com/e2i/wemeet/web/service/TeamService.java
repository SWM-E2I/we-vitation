package com.e2i.wemeet.web.service;

import com.e2i.wemeet.web.domain.team.TeamRepository;
import com.e2i.wemeet.web.exception.notfound.NotFoundTeamCodeException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class TeamService {

    private final TeamRepository teamRepository;

    @Transactional(readOnly = true)
    public String getTeamLeaderName(final String teamCode) {
        return teamRepository.findByTeamCode(teamCode)
            .orElseThrow(NotFoundTeamCodeException::new)
            .getTeamLeader()
            .getNickname();
    }

}
