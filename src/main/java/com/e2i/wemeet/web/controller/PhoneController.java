package com.e2i.wemeet.web.controller;

import com.e2i.wemeet.web.dto.phone.PhoneRequestDto;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Slf4j
@RequestMapping("/v1/web")
@Controller
public class PhoneController {

    @GetMapping("/phone")
    public String phone(Model model) {
        PhoneRequestDto phoneRequestDto = new PhoneRequestDto("010");
        model.addAttribute("phoneRequest", phoneRequestDto);
        model.addAttribute("bindingResult", new BeanPropertyBindingResult(phoneRequestDto, "phoneRequest"));
        return "phone/phone_input";
    }

    @PostMapping("/phone")
    public String issue(@Valid @ModelAttribute PhoneRequestDto phoneRequestDto, BindingResult bindingResult,
        Model model, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            log.info("binding result has errors: {}", bindingResult);
            model.addAttribute("phoneRequest", phoneRequestDto);
            model.addAttribute("bindingResult", bindingResult); // Add bindingResult to the model

            return "phone/phone_input";
        }

        redirectAttributes.addAttribute("phone", phoneRequestDto.phoneNumber());
        return "redirect:/v1/web/phone/cred";
    }

    @GetMapping("/phone/cred")
    public String validate() {
        return "phone/phone_validate";
    }

}
