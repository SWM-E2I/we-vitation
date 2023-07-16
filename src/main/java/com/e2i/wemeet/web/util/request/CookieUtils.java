package com.e2i.wemeet.web.util.request;

import com.e2i.wemeet.web.controller.CookieEnv;
import com.e2i.wemeet.web.exception.badrequest.CookieNotFoundException;
import jakarta.servlet.http.Cookie;
import java.util.Arrays;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class CookieUtils {
    private CookieUtils() {
    }

    public static String getCookieValue(Cookie[] cookies, CookieEnv cookieEnv) throws CookieNotFoundException {
        return Arrays.stream(cookies)
            .filter(cookie -> cookie.getName().equals(cookieEnv.getKey()))
            .findFirst()
            .map(Cookie::getValue)
            .orElseThrow(() -> {
                log.info("{} : Not Found In Cookie", cookieEnv.name());
                return new CookieNotFoundException();
            });
    }
}
