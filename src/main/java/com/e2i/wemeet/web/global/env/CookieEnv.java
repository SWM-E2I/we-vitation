package com.e2i.wemeet.web.global.env;

import java.time.Duration;
import lombok.Getter;

@Getter
public enum CookieEnv {
    TEAM_CODE("tc",  null, Duration.ofDays(7)),
    PERSONAL_IDENTIFIER("idt", "-", Duration.ofDays(7)),
    PHONE_NUMBER("pn", null, Duration.ofDays(3)),
    EMAIL("em", null, Duration.ofDays(3))
    ;

    private final String key;
    private final String delimiter;
    private final Duration expireDuration;

    CookieEnv(String key, String delimiter, Duration expireDuration) {
        this.key = key;
        this.delimiter = delimiter;
        this.expireDuration = expireDuration;
    }

    public int getExpireSeconds() {
        return (int) expireDuration.getSeconds();
    }
}
