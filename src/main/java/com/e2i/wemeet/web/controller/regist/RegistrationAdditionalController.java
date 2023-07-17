package com.e2i.wemeet.web.controller.regist;

import com.e2i.wemeet.web.controller.CookieEnv;
import com.e2i.wemeet.web.dto.register.RegisterAdditionalRequestDto;
import com.e2i.wemeet.web.service.registration.RegistrationService;
import com.e2i.wemeet.web.util.request.CookieUtils;
import com.e2i.wemeet.web.util.secure.Cryptography;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@RequiredArgsConstructor
@RequestMapping("/v1/web/register")
@Controller
public class RegistrationAdditionalController {

    private final RegistrationService registrationService;
    private final Cryptography cryptography;

    @GetMapping("/additional")
    public String registerAdditional() {
        return "register/register_additional";
    }

    @ResponseBody
    @PostMapping("/additional")
    public Long register(@RequestBody RegisterAdditionalRequestDto requestDto, HttpServletRequest request) {
        String memberDetailKey = getMemberDetailKey(request);
        return registrationService.saveRegistration(requestDto, memberDetailKey);
    }

    private String getMemberDetailKey(HttpServletRequest request) {
        String teamCode = CookieUtils.getCookieValue(request.getCookies(), CookieEnv.TEAM_CODE);
        String phoneNumber = cryptography.decrypt(CookieUtils.getCookieValue(request.getCookies(), CookieEnv.PHONE_NUMBER));
        return teamCode + "-" + phoneNumber;
    }

}
