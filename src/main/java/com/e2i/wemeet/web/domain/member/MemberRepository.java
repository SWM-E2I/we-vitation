package com.e2i.wemeet.web.domain.member;

import com.e2i.wemeet.web.domain.team.Team;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByPhoneNumber(String phoneNumber);

    @Query("SELECT COUNT(m) FROM Member m WHERE m.team = :team")
    int countByTeam(@Param("team") Team team);
}
