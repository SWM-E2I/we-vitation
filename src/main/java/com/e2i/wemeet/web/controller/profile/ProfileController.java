package com.e2i.wemeet.web.controller.profile;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/v1/web/profile")
@Controller
public class ProfileController {
    @GetMapping
    public String profie() {
        return "profile/profile";
    }
}
