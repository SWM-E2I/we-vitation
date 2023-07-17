package com.e2i.wemeet.web.controller;

import java.time.Duration;
import lombok.Getter;

@Getter
public enum CookieEnv {
    TEAM_CODE("teamCode", Duration.ofDays(7)),
    PHONE_NUMBER("phone", Duration.ofDays(3)),
    PERSONAL_IDENTIFIER("idt", Duration.ofDays(7)),
    ;

    private final String key;
    private final Duration expireDuration;

    CookieEnv(String key, Duration expireDuration) {
        this.key = key;
        this.expireDuration = expireDuration;
    }

    public int getExpireSeconds() {
        return (int) expireDuration.getSeconds();
    }
}