package com.e2i.wemeet.web.global.interceptor;

import com.e2i.wemeet.web.controller.CookieEnv;
import com.e2i.wemeet.web.exception.badrequest.CookieNotFoundException;
import com.e2i.wemeet.web.util.request.CookieUtils;
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
        // Cookie 가 있는지 검증 없으면 에러 페이지로 리다이렉트
        try {
            CookieUtils.getCookieValue(request.getCookies(), CookieEnv.TEAM_CODE);
            return true;
        } catch (CookieNotFoundException e) {
            response.sendRedirect("/v1/web/error");
            return false;
        }
    }
}
