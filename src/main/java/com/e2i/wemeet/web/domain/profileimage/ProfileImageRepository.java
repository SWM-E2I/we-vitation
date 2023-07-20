package com.e2i.wemeet.web.domain.profileimage;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProfileImageRepository extends JpaRepository<ProfileImage, Long> {

    Optional<ProfileImage> findByMemberMemberIdAndIsMain(Long memberId, boolean isMain);

    @Query("SELECT pi FROM ProfileImage pi WHERE pi.member.memberId = :memberId AND pi.profileImageId = :profileImageId")
    Optional<ProfileImage> findByMemberIdAndProfileImageId(Long memberId, Long profileImageId);
}
