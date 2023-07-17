package com.e2i.wemeet.web.controller.univ;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/v1/web/college")
@Controller
public class CollegeController {

    @GetMapping
    public String college() {
        return "college/college_input";
    }
}
