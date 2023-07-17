package com.e2i.wemeet.web.controller.phone;

import com.e2i.wemeet.web.controller.CookieEnv;
import com.e2i.wemeet.web.dto.phone.CredentialRequestDto;
import com.e2i.wemeet.web.dto.phone.PhoneRequestDto;
import com.e2i.wemeet.web.exception.CustomException;
import com.e2i.wemeet.web.service.credential.SmsCredentialService;
import com.e2i.wemeet.web.service.team.TeamService;
import com.e2i.wemeet.web.util.request.CookieUtils;
import com.e2i.wemeet.web.util.secure.Cryptography;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
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
    private final TeamService teamService;
    private final Cryptography cryptography;

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
        final String phoneNumber = addPrefixOnPhoneNumber(phoneRequestDto.phone());
        smsCredentialService.issue(phoneNumber);

        redirectAttributes.addAttribute("phone", phoneRequestDto.phone());
        return "redirect:/v1/web/phone/cred";
    }

    @ResponseBody
    @PostMapping("/phone/reissue")
    public String reissue(@Valid @RequestBody PhoneRequestDto phoneRequestDto) {
        final String phoneNumber = addPrefixOnPhoneNumber(phoneRequestDto.phone());
        smsCredentialService.issue(phoneNumber);

        return "CREDENTIAL_REISSUE_SUCCESS";
    }

    @GetMapping("/phone/cred")
    public String credential(@RequestParam String phone, Model model) {
        CredentialRequestDto credentialRequestDto = new CredentialRequestDto(phone, null);
        model.addAttribute("credentialRequest", credentialRequestDto);
        model.addAttribute("bindingResult", new BeanPropertyBindingResult(credentialRequestDto, "phoneRequest"));

        return "phone/phone_validate";
    }

    @PostMapping("/phone/cred")
    public String verify(@Valid @ModelAttribute CredentialRequestDto credentialRequestDto,
        BindingResult bindingResult, Model model,
        HttpServletRequest request, HttpServletResponse response) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("credentialRequest", credentialRequestDto);
            setBindingError(bindingResult, model);

            return "phone/phone_validate";
        }

        final String phoneNumber = addPrefixOnPhoneNumber(credentialRequestDto.phone());

        try {
            smsCredentialService.verify(phoneNumber, credentialRequestDto.credential());
        } catch (CustomException e) {
            model.addAttribute("credentialRequest", credentialRequestDto);
            bindingResult.addError(new FieldError("credential", "credential", e.getMessage()));
            setBindingError(bindingResult, model);

            return "phone/phone_validate";
        }

        String teamCode = CookieUtils.getCookieValue(request.getCookies(), CookieEnv.TEAM_CODE);

        // 번호 인증을 받은 사용자가 이미 가입되어있는 회원이라면 마지막 단계로 이동
        if (teamService.setMemberByPhoneNumberIfExist(teamCode, phoneNumber)) {
            return "redirect:/v1/web/fin";
        }

        response.addCookie(CookieUtils.createCookie(cryptography.encrypt(phoneNumber), CookieEnv.PHONE_NUMBER));
        return "redirect:/v1/web/register";
    }

    private String addPrefixOnPhoneNumber(String phone) {
        return phone.replaceFirst("0", "+82");
    }

    private void setBindingError(BindingResult bindingResult, Model model) {
        log.info("binding result has errors: {}", bindingResult);
        model.addAttribute("bindingResult", bindingResult);
    }
}
