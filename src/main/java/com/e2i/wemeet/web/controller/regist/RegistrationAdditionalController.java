package com.e2i.wemeet.web.controller.regist;

import com.e2i.wemeet.web.dto.register.RegisterAdditionalRequestDto;
import com.e2i.wemeet.web.global.resolver.phone.PhoneNumberInfo;
import com.e2i.wemeet.web.global.resolver.phone.PhoneNumberValue;
import com.e2i.wemeet.web.global.resolver.teamCode.TeamCodeInfo;
import com.e2i.wemeet.web.global.resolver.teamCode.TeamCodeValue;
import com.e2i.wemeet.web.service.registration.RegistrationService;
import com.e2i.wemeet.web.util.serialize.SerializeUtils;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
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

    @GetMapping("/additional")
    public String registerAdditional() {
        return "register/register_additional";
    }

    @ResponseBody
    @PostMapping("/additional")
    public Long register(@RequestBody RegisterAdditionalRequestDto requestDto,
        @PhoneNumberValue PhoneNumberInfo phoneNumberInfo,
        @TeamCodeValue TeamCodeInfo teamCodeInfo, HttpServletResponse response) throws IOException {

        String memberDetailKey = SerializeUtils.getMemberDetailKey(teamCodeInfo.teamCode(), phoneNumberInfo.phoneNumber());
        Long memberId = registrationService.saveRegistration(requestDto, memberDetailKey);

        if (memberId == null) response.sendRedirect("/v1/web/register/basic");
        return memberId;
    }
}
