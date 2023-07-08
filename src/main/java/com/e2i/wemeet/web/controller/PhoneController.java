package com.e2i.wemeet.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/v1/web")
@Controller
public class PhoneController {

    @GetMapping("/phone")
    public String phone() {
        return "phone/phone_input";
    }

}
