package com.e2i.wemeet.web.domain.team;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TeamRepository extends JpaRepository<Team, Long> {

    @Query("SELECT t FROM Team t JOIN FETCH t.teamLeader WHERE t.teamCode = :teamCode")
    Optional<Team> findByTeamCode(String teamCode);
}
