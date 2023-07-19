package com.e2i.wemeet.web.service.credential.sms;

import com.e2i.wemeet.web.service.credential.CredentialService;

public interface SmsCredentialService extends CredentialService {
    void verify(final String target, final String credential);
}
