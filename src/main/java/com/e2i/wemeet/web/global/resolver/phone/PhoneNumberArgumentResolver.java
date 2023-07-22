package com.e2i.wemeet.web.global.resolver.phone;

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
public class PhoneNumberArgumentResolver implements HandlerMethodArgumentResolver {

    private final Cryptography cryptography;

    public PhoneNumberArgumentResolver(Cryptography cryptography) {
        this.cryptography = cryptography;
    }

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        boolean hasInvitationAnnotation = parameter.hasParameterAnnotation(PhoneNumberValue.class);
        boolean hasInvitationType = PhoneNumberInfo.class.isAssignableFrom(parameter.getParameterType());

        return hasInvitationType && hasInvitationAnnotation;
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
        NativeWebRequest webRequest, WebDataBinderFactory binderFactory) {
        HttpServletRequest request = (HttpServletRequest) webRequest.getNativeRequest();

        return getPhoneNumberValue(request);
    }

    private String getPhoneNumberValue(final HttpServletRequest request) {
        String paramValue = request.getParameter(ParamEnv.PHONE.getKey());
        if (paramValue != null) {
            return cryptography.decrypt(paramValue);
        }

        String cookieValue = CookieUtils.getCookieValue(request.getCookies(), CookieEnv.PHONE_NUMBER);
        return cryptography.decrypt(cookieValue);
    }
}
