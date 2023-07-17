package com.e2i.wemeet.web.controller.main;

import com.e2i.wemeet.web.controller.CookieEnv;
import com.e2i.wemeet.web.service.team.TeamService;
import com.e2i.wemeet.web.util.request.CookieUtils;
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

    @GetMapping("/main")
    public String mainPage(@RequestParam String code, HttpServletResponse response, Model model) {
        response.addCookie(CookieUtils.createCookie(code, CookieEnv.TEAM_CODE));

        // String leaderName = teamService.getTeamLeaderName(code);
        model.addAttribute("leaderName", "leaderName");

        return "/main/main_page";
    }
}
