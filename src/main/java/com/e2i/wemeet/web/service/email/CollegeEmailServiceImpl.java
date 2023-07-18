package com.e2i.wemeet.web.service.email;

import com.e2i.wemeet.web.domain.member.MemberRepository;
import com.e2i.wemeet.web.exception.badrequest.EmailDomainNotMatchException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CollegeEmailServiceImpl implements CollegeEmailService {

    private final EmailMatcher emailMatcher;
    private final MemberRepository memberRepository;

    @Override
    public void verifyDomain(final String email, final Long memberId) {
        if (!emailMatcher.matchesDomain(email, memberId)) {
            throw new EmailDomainNotMatchException();
        }
    }

    @Override
    public void saveEmail(final String email, final Long memberId) {
        memberRepository.findById(memberId).ifPresent(member ->
            member.getCollegeInfo().saveMail(email));
    }
}
