package com.e2i.wemeet.web.controller.phone;

import com.e2i.wemeet.web.exception.internal.InternalServerException;
import com.e2i.wemeet.web.global.env.CookieEnv;
import com.e2i.wemeet.web.global.resolver.phone.PhoneNumberInfo;
import com.e2i.wemeet.web.global.resolver.phone.PhoneNumberValue;
import com.e2i.wemeet.web.global.resolver.teamCode.TeamCodeInfo;
import com.e2i.wemeet.web.global.resolver.teamCode.TeamCodeValue;
import com.e2i.wemeet.web.service.PhoneRouteService;
import com.e2i.wemeet.web.service.team.RegistrationStep;
import com.e2i.wemeet.web.service.team.TeamService;
import com.e2i.wemeet.web.util.request.CookieUtils;
import com.e2i.wemeet.web.util.secure.Cryptography;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@RequestMapping("/v1/web/phone")
@Controller
public class PhoneRouteController {

    private final PhoneRouteService phoneRouteService;
    private final TeamService teamService;
    private final Cryptography cryptography;

    @GetMapping("/route")
    public String phoneRoute(@PhoneNumberValue PhoneNumberInfo phoneNumber,
                             @TeamCodeValue TeamCodeInfo teamCodeInfo,
                             HttpServletResponse response) {
        RegistrationStep registrationStep = phoneRouteService.getRegistrationStep(phoneNumber.phoneNumber());

        switch (registrationStep) {
            case EXIST -> {
                String membersTeamLeader = phoneRouteService.getMembersTeamLeaderName(
                    phoneNumber.phoneNumber());
                return "redirect:/v1/web/error/exist?leaderName=" + membersTeamLeader;
            }
            case BASIC_INFO -> {
                addPhoneCookie(phoneNumber.phoneNumber(), response);
                return "redirect:/v1/web/register";
            }
            case EMAIL_AUTHENTICATION -> {
                addIdentifier(teamCodeInfo.teamCode(), phoneNumber.phoneNumber(), response);
                return "redirect:/v1/web/email";
            }
            case PROFILE_IMAGE -> {
                addIdentifier(teamCodeInfo.teamCode(), phoneNumber.phoneNumber(), response);
                return "redirect:/v1/web/profile";
            }
            case DONE -> {
                teamService.registerTeam(teamCodeInfo.teamCode(), phoneNumber.phoneNumber());
                addIdentifier(teamCodeInfo.teamCode(), phoneNumber.phoneNumber(), response);
                return "redirect:/v1/web/finish";
            }
            default -> throw new InternalServerException("Unexpected Registration Value: " + registrationStep);
        }
    }

    private void addPhoneCookie(final String phoneNumber, HttpServletResponse response) {
        Cookie phoneCookie = CookieUtils.createCookie(
            cryptography.encrypt(phoneNumber),
            CookieEnv.PHONE_NUMBER);

        response.addCookie(phoneCookie);
    }

    private void addIdentifier(final String teamCode, final String phoneNumber, HttpServletResponse response) {
        Long memberId = phoneRouteService.getMemberIdByPhoneNumber(phoneNumber);

        Cookie identifier = CookieUtils.createCookie(
            cryptography.encrypt(CookieUtils.getIdentifier(teamCode, memberId)),
            CookieEnv.PERSONAL_IDENTIFIER);

        response.addCookie(identifier);
    }
}
