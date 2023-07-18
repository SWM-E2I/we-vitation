package com.e2i.wemeet.web.service.credential.ses;

import com.e2i.wemeet.web.service.credential.CredentialService;

public interface EmailCredentialService extends CredentialService {
    void verify(String target, String credential, Long memberId);
}
