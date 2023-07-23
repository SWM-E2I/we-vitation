package com.e2i.wemeet.web.service;

import com.e2i.wemeet.web.domain.member.Member;
import com.e2i.wemeet.web.domain.member.MemberRepository;
import com.e2i.wemeet.web.domain.profileimage.ProfileImageRepository;
import com.e2i.wemeet.web.service.team.RegistrationStep;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class PhoneRouteService {

    private final ProfileImageRepository profileImageRepository;
    private final MemberRepository memberRepository;

    @Transactional(readOnly = true)
    public RegistrationStep getRegistrationStep(final String phoneNumber) {
        Member member = memberRepository.findByPhoneNumber(phoneNumber)
            .orElse(null);

        if (member == null) {
            return RegistrationStep.BASIC_INFO;
        }
        if (member.getTeam() != null) {
            return RegistrationStep.EXIST;
        }
        if (!member.isEmailAuthenticated()) {
            return RegistrationStep.EMAIL_AUTHENTICATION;
        }
        if (profileImageRepository.countByMember(member) == 0) {
            return RegistrationStep.PROFILE_IMAGE;
        }
        return RegistrationStep.DONE;
    }

    @Transactional(readOnly = true)
    public Long getMemberIdByPhoneNumber(final String phoneNumber) {
        return memberRepository.findByPhoneNumber(phoneNumber)
            .map(Member::getMemberId)
            .orElse(null);
    }

    @Transactional(readOnly = true)
    public String getMembersTeamLeaderName(final String phoneNumber) {
        return memberRepository.findTeamLeaderNameByTeam(phoneNumber);
    }
}
