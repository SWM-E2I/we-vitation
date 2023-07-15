package com.e2i.wemeet.web.service.credential;

public interface SmsCredentialService extends CredentialService {
    void verify(final String target, final String credential);
}
