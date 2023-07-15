package com.e2i.wemeet.web.controller;

import static com.e2i.wemeet.web.controller.CookieEnv.TEAM_CODE;

import com.e2i.wemeet.web.service.team.TeamService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import java.time.Duration;
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
        response.addCookie(generateTeamCodeCookie(code));

        // String leaderName = teamService.getTeamLeaderName(code);
        model.addAttribute("leaderName", "leaderName");

        return "/main/main_page";
    }

    private Cookie generateTeamCodeCookie(String code) {
        Cookie teamCode = new Cookie(TEAM_CODE.getName(), code);
        teamCode.setHttpOnly(true);
        teamCode.setMaxAge((int) Duration.ofDays(7).getSeconds());
        teamCode.setPath("/");
        teamCode.setSecure(true);

        return teamCode;
    }
}
