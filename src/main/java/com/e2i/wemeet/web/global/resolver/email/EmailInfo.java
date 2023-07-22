package com.e2i.wemeet.web.global.resolver.email;

import lombok.Getter;

@Getter
public record EmailInfo(String email) {
    public String getEmailName() {
        return this.email.split("@")[0];
    }
    public String getEmailDomain() {
        return this.email.split("@")[1];
    }
}
