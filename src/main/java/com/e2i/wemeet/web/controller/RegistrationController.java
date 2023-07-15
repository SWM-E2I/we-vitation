package com.e2i.wemeet.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/v1/web/register")
@Controller
public class RegistrationController {

    @GetMapping
    public String register() {
        return "register/register";
    }
}
