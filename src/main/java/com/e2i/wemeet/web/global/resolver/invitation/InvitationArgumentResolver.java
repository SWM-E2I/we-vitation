package com.e2i.wemeet.web.global.resolver.invitation;

import com.e2i.wemeet.web.global.env.CookieEnv;
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
public class InvitationArgumentResolver implements HandlerMethodArgumentResolver {

    private final Cryptography cryptography;

    public InvitationArgumentResolver(Cryptography cryptography) {
        this.cryptography = cryptography;
    }

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        boolean hasInvitationAnnotation = parameter.hasParameterAnnotation(Invitation.class);
        boolean hasInvitationType = InvitationInfo.class.isAssignableFrom(
            parameter.getParameterType());
        return hasInvitationType && hasInvitationAnnotation;
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
        NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        HttpServletRequest request = (HttpServletRequest) webRequest.getNativeRequest();
        String identifier = cryptography.decrypt(
            CookieUtils.getCookieValue(request.getCookies(), CookieEnv.PERSONAL_IDENTIFIER)
        );

        return InvitationInfo.of(identifier);
    }
}
