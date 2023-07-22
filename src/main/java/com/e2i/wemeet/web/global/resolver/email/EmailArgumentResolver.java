package com.e2i.wemeet.web.global.resolver.email;

import com.e2i.wemeet.web.global.env.CookieEnv;
import com.e2i.wemeet.web.global.env.ParamEnv;
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
public class EmailArgumentResolver implements HandlerMethodArgumentResolver {

    private final Cryptography cryptography;

    public EmailArgumentResolver(Cryptography cryptography) {
        this.cryptography = cryptography;
    }

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        boolean hasInvitationAnnotation = parameter.hasParameterAnnotation(EmailValue.class);
        boolean hasInvitationType = EmailInfo.class.isAssignableFrom(parameter.getParameterType());

        return hasInvitationType && hasInvitationAnnotation;
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
        NativeWebRequest webRequest, WebDataBinderFactory binderFactory) {
        HttpServletRequest request = (HttpServletRequest) webRequest.getNativeRequest();
        
        return getEmailValue(request);
    }

    private String getEmailValue(final HttpServletRequest request) {
        String paramValue = request.getParameter(ParamEnv.EMAIL.getKey());
        if (paramValue != null) {
            return cryptography.decrypt(paramValue);
        }

        String cookieValue = CookieUtils.getCookieValue(request.getCookies(), CookieEnv.EMAIL);
        return cryptography.decrypt(cookieValue);
    }
}
