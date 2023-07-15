package com.e2i.wemeet.web.service.credential;

public interface CredentialService {
    void issue(String target);

    boolean matches(String target, String input);
}
