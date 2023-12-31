package com.e2i.wemeet.web.global.env;

import lombok.Getter;

@Getter
public enum ParamEnv {
    EMAIL("em"),
    PHONE("pn")
    ;

    private final String key;

    ParamEnv(String key) {
        this.key = key;
    }
}
