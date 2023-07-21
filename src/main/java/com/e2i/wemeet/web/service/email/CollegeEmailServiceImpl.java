package com.e2i.wemeet.web.service.email;

import com.e2i.wemeet.web.domain.member.Member;
import com.e2i.wemeet.web.domain.member.MemberRepository;
import com.e2i.wemeet.web.exception.badrequest.EmailDomainNotMatchException;
import com.e2i.wemeet.web.exception.notfound.MemberNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
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

    @Transactional
    @Override
    public void saveEmail(final String email, final Long memberId) {
        log.info("Save email: {} / memberId: {}", email, memberId);
        Member member = memberRepository.findById(memberId)
            .orElseThrow(MemberNotFoundException::new);

        member.getCollegeInfo().saveMail(email);
    }
}
