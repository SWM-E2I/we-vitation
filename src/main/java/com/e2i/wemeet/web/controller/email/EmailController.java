package com.e2i.wemeet.web.controller.email;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/v1/web/email")
@Controller
public class EmailController {

    @GetMapping
    public String email() {
        return "email/email_input";
    }
}
