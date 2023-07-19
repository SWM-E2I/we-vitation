package com.e2i.wemeet.web.service.email;

import com.e2i.wemeet.web.domain.member.Colleges;
import com.e2i.wemeet.web.domain.member.Member;
import com.e2i.wemeet.web.domain.member.MemberRepository;
import com.e2i.wemeet.web.exception.notfound.MemberNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class EnumDomainMatcher implements EmailMatcher {

    private final MemberRepository memberRepository;

    @Override
    public boolean matchesDomain(final String email, final Long memberId) {
        Colleges college = getCollege(memberId);

        return email.equals(college.getEmailDomain());
    }

    private Colleges getCollege(final Long memberId) {
        Member member = memberRepository.findById(memberId)
            .orElseThrow(MemberNotFoundException::new);
        String collegeName = member.getCollegeInfo().getCollege();
        return Colleges.findByCollegeName(collegeName);
    }
}
