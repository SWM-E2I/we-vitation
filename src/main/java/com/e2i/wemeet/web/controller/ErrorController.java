package com.e2i.wemeet.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/v1/web/error")
@Controller
public class ErrorController {

    @GetMapping
    public String errorPage() {
        return "error/error_return";
    }

    @GetMapping("/full")
    public String errorPageFull() {
        return "error/error_team_full";
    }

    @GetMapping("/exist")
    public String errorPageExist(@RequestParam String leaderName, Model model) {
        model.addAttribute("leaderName", leaderName);
        return "error/error_team_exist";
    }
}
