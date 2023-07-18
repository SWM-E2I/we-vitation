package com.e2i.wemeet.web.service.email;

public interface CollegeEmailService {
    void verifyDomain(String email, Long memberId);
    void saveEmail(String email, Long memberId);
}
