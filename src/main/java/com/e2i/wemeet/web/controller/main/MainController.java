package com.e2i.wemeet.web.controller.main;

import com.e2i.wemeet.web.global.env.CookieEnv;
import com.e2i.wemeet.web.global.resolver.invitation.Invitation;
import com.e2i.wemeet.web.global.resolver.invitation.InvitationInfo;
import com.e2i.wemeet.web.service.team.TeamService;
import com.e2i.wemeet.web.util.request.CookieUtils;
import com.e2i.wemeet.web.util.secure.Cryptography;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequiredArgsConstructor
@RequestMapping("/v1/web")
@Controller
public class MainController {

    private final TeamService teamService;
    private final Cryptography cryptography;

    @GetMapping("/main")
    public String mainPage(@RequestParam String code, HttpServletResponse response, Model model) {
        if (teamService.checkFullRegistered(code)) {
            return "redirect:/v1/web/error/full";
        }

        response.addCookie(CookieUtils.createCookie(cryptography.encrypt(code), CookieEnv.TEAM_CODE));
        String leaderName = teamService.getTeamLeaderName(code);
        model.addAttribute("leaderName", leaderName);

        return "main/main_page";
    }

    @GetMapping("/finish")
    public String finishPage(@Invitation InvitationInfo invitationInfo, Model model) {
        String leaderName = teamService.getTeamLeaderName(invitationInfo.teamCode());
        model.addAttribute("leaderName", leaderName);

        return "finish/finish";
    }
}
