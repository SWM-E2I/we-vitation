package com.e2i.wemeet.web.service.email;

public interface EmailMatcher {
    boolean matchesDomain(String email, Long memberId);
}
