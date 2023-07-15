package com.e2i.wemeet.web.controller;

import lombok.Getter;

@Getter
public enum CookieEnv {
    TEAM_CODE("teamCode");

    private final String name;

    CookieEnv(String name) {
        this.name = name;
    }
}
