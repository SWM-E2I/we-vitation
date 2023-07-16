package com.e2i.wemeet.web.controller;

import lombok.Getter;

@Getter
public enum CookieEnv {
    TEAM_CODE("teamCode"),
    PHONE_NUMBER("phone")
    ;

    private final String key;

    CookieEnv(String key) {
        this.key = key;
    }
}
