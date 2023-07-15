package com.e2i.wemeet.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/v1/web/error")
@Controller
public class ErrorController {

    @GetMapping
    public String errorPage() {
        return "error/error_return";
    }

}
