package com.e2i.wemeet.web.controller.regist;

import com.e2i.wemeet.web.dto.register.RegisterBasicRequestDto;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/v1/web/register")
@Controller
public class RegistrationController {

    @GetMapping
    public String registerBasic(@Valid @ModelAttribute RegisterBasicRequestDto registerBasicRequestDto) {


        return "register/register_basic";
    }

    @GetMapping("/add")
    public String registerAdditional() {

        return "register/register_additional";
    }
}
