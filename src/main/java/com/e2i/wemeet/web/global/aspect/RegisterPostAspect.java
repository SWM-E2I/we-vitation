package com.e2i.wemeet.web.global.aspect;

import com.e2i.wemeet.web.global.env.CookieEnv;
import com.e2i.wemeet.web.util.request.CookieUtils;
import com.e2i.wemeet.web.util.secure.Cryptography;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class RegisterPostAspect {

    private final HttpServletRequest request;
    private final HttpServletResponse response;
    private final Cryptography cryptography;

    public RegisterPostAspect(HttpServletRequest request, HttpServletResponse response, Cryptography cryptography) {
        this.request = request;
        this.response = response;
        this.cryptography = cryptography;
    }

    @AfterReturning(
        value = "execution(* com.e2i.wemeet.web.controller.regist.RegistrationAdditionalController.register(..))",
        returning = "result"
    )
    public void afterRegisterPost(Object result) {
        log.info("entrance");
        Long memberId = (Long) result;
        handleCookies(request, response, memberId);
    }

    private void handleCookies(HttpServletRequest request, HttpServletResponse response, Long memberId) {
        Cookie teamCodeCookie = CookieUtils.getCookie(request.getCookies(), CookieEnv.TEAM_CODE);
        Cookie phoneCookie = new Cookie(CookieEnv.PHONE_NUMBER.getKey(), null);
        phoneCookie.setMaxAge(0);
        phoneCookie.setValue(null);
        phoneCookie.setPath("/");

        String teamCode = teamCodeCookie.getValue();
        String identifier = cryptography.encrypt(teamCode + "-" + memberId);
        Cookie identifierCookie = CookieUtils.createCookie(identifier, CookieEnv.PERSONAL_IDENTIFIER);

        response.addCookie(phoneCookie);
        response.addCookie(identifierCookie);

        log.info("Identifier Cookie added: team {} / memberId {}", teamCode, memberId);
    }

}
