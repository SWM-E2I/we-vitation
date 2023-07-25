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

    @Query("SELECT l.nickname FROM Member m JOIN m.team t JOIN t.teamLeader l WHERE m.phoneNumber = :phoneNumber")
    String findTeamLeaderNameByTeam(String phoneNumber);

    void deleteByPhoneNumber(String phoneNumber);

    @Query("""
             DELETE FROM Member m
             WHERE m.memberId NOT IN
             (SELECT m.memberId FROM Member m LEFT JOIN m.team t ON t.teamLeader.memberId = m.memberId)
        """
    )
    void deleteAllNewMember();
}
