package com.e2i.wemeet.web.global.resolver.teamCode;

import com.e2i.wemeet.web.controller.CookieEnv;
import com.e2i.wemeet.web.controller.ParamEnv;
import com.e2i.wemeet.web.util.request.CookieUtils;
import com.e2i.wemeet.web.util.secure.Cryptography;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

@Slf4j
@Component
public class TeamCodeArgumentResolver implements HandlerMethodArgumentResolver {

    private final Cryptography cryptography;

    public TeamCodeArgumentResolver(Cryptography cryptography) {
        this.cryptography = cryptography;
    }

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        boolean hasInvitationAnnotation = parameter.hasParameterAnnotation(TeamCodeValue.class);
        boolean hasInvitationType = TeamCodeInfo.class.isAssignableFrom(parameter.getParameterType());

        return hasInvitationType && hasInvitationAnnotation;
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
        NativeWebRequest webRequest, WebDataBinderFactory binderFactory) {
        HttpServletRequest request = (HttpServletRequest) webRequest.getNativeRequest();
        
        return getEmailValue(request);
    }

    private String getEmailValue(final HttpServletRequest request) {
        String cookieValue = CookieUtils.getCookieValue(request.getCookies(), CookieEnv.TEAM_CODE);
        return cryptography.decrypt(cookieValue);
    }
}
