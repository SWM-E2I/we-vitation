package com.e2i.wemeet.web.global.interceptor;

import com.e2i.wemeet.web.controller.CookieEnv;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Arrays;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

@Slf4j
public class TeamCheckInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
        Object handler) throws Exception {
        String teamCode = getTeamCode(request);

        if (teamCode == null) {
            response.sendRedirect("/v1/web/error");
            return false;
        }

        return true;
    }

    private String getTeamCode(HttpServletRequest request) {
        return Arrays.stream(request.getCookies())
            .filter(cookie -> cookie.getName().equals(CookieEnv.TEAM_CODE.getName()))
            .findFirst()
            .map(Cookie::getValue)
            .orElseGet(() -> {
                log.error("Team Code Not Found In Cookie");
                return null;
            });
    }
}
