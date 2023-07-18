package com.e2i.wemeet.web.service.email;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Primary
@Component
public class AcceptEmailMatcher implements EmailMatcher {

    @Override
    public boolean matchesDomain(final String email, final Long memberId) {
        return true;
    }
}
