package com.e2i.wemeet.web.controller.phone;

import com.e2i.wemeet.web.controller.ParamEnv;
import com.e2i.wemeet.web.dto.phone.PhoneCredentialRequestDto;
import com.e2i.wemeet.web.dto.phone.PhoneRequestDto;
import com.e2i.wemeet.web.exception.CustomException;
import com.e2i.wemeet.web.service.credential.sms.SmsCredentialService;
import com.e2i.wemeet.web.service.team.TeamService;
import com.e2i.wemeet.web.util.secure.Cryptography;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/v1/web")
@Controller
public class PhoneController {

    private final SmsCredentialService smsCredentialService;

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
            model.addAttribute("phoneRequest", phoneRequestDto);
            setBindingError(bindingResult, model);

            return "phone/phone_input";
        }
        final String phoneNumber = phoneRequestDto.getPrefixedPhoneNumber();
        smsCredentialService.issue(phoneNumber);

        redirectAttributes.addAttribute(ParamEnv.PHONE.getKey(), phoneRequestDto.phone());
        return "redirect:/v1/web/phone/cred";
    }

    @ResponseBody
    @PostMapping("/phone/reissue")
    public String reissue(@Valid @RequestBody PhoneRequestDto phoneRequestDto) {
        smsCredentialService.issue(phoneRequestDto.getPrefixedPhoneNumber());

        return "CREDENTIAL_REISSUE_SUCCESS";
    }

    @GetMapping("/phone/cred")
    public String credential(@RequestParam String phone, Model model) {
        PhoneCredentialRequestDto phoneCredentialRequestDto = new PhoneCredentialRequestDto(phone, null);
        model.addAttribute("credentialRequest", phoneCredentialRequestDto);
        model.addAttribute("bindingResult", new BeanPropertyBindingResult(phoneCredentialRequestDto, "phoneRequest"));

        return "phone/phone_validate";
    }

    @PostMapping("/phone/cred")
    public String verify(@Valid @ModelAttribute PhoneCredentialRequestDto phoneCredentialRequestDto,
        BindingResult bindingResult, Model model,
        RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("credentialRequest", phoneCredentialRequestDto);
            setBindingError(bindingResult, model);

            return "phone/phone_validate";
        }

        try {
            smsCredentialService.verify(phoneCredentialRequestDto.getPrefixedPhoneNumber(), phoneCredentialRequestDto.credential());
        } catch (CustomException e) {
            model.addAttribute("credentialRequest", phoneCredentialRequestDto);
            bindingResult.addError(new FieldError("credential", "credential", e.getMessage()));
            setBindingError(bindingResult, model);

            return "phone/phone_validate";
        }

        redirectAttributes.addAttribute(ParamEnv.PHONE.getKey(), phoneCredentialRequestDto.phone());
        return "redirect:/v1/web/phone/route";
    }

    private void setBindingError(BindingResult bindingResult, Model model) {
        log.info("binding result has errors: {}", bindingResult);
        model.addAttribute("bindingResult", bindingResult);
    }
}
