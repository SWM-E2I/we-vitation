package com.e2i.wemeet.web.controller.email;

import com.e2i.wemeet.web.domain.member.Colleges;
import com.e2i.wemeet.web.dto.email.EmailCredentialRequestDto;
import com.e2i.wemeet.web.dto.email.EmailRequestDto;
import com.e2i.wemeet.web.exception.CustomException;
import com.e2i.wemeet.web.global.env.ParamEnv;
import com.e2i.wemeet.web.global.resolver.email.EmailInfo;
import com.e2i.wemeet.web.global.resolver.email.EmailValue;
import com.e2i.wemeet.web.global.resolver.invitation.Invitation;
import com.e2i.wemeet.web.global.resolver.invitation.InvitationInfo;
import com.e2i.wemeet.web.service.credential.ses.EmailCredentialService;
import com.e2i.wemeet.web.service.email.CollegeEmailService;
import com.e2i.wemeet.web.util.secure.Cryptography;
import jakarta.validation.Valid;
import java.util.Arrays;
import java.util.List;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/v1/web/email")
@Controller
public class EmailController {

    private final EmailCredentialService emailCredentialService;
    private final CollegeEmailService collegeEmailService;
    private final Cryptography cryptography;

    @ModelAttribute("domains")
    public List<String> domains() {
        return Arrays.stream(Colleges.values())
            .map(Colleges::getEmailDomain)
            .toList();
    }

    @GetMapping
    public String email(Model model) {
        EmailRequestDto emailDto = new EmailRequestDto(null, null);
        model.addAttribute("emailDto", emailDto);
        model.addAttribute("bindingResult", new BeanPropertyBindingResult(emailDto, "emailDto"));

        return "email/email_input";
    }

    @PostMapping
    public String issue(@Valid @ModelAttribute EmailRequestDto emailDto, BindingResult bindingResult,
            Model model, @Invitation InvitationInfo invitationInfo,
            RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("emailDto", emailDto);
            setBindingError(bindingResult, model);

            return "email/email_input";
        }
        final String email = emailDto.getEmail();

        try {
            collegeEmailService.verifyDomain(emailDto.emailDomain(), invitationInfo.memberId());
            emailCredentialService.issue(emailDto.getEmail());
        } catch (CustomException e) {
            model.addAttribute("emailDto", emailDto);
            bindingResult.addError(new FieldError("emailDomain", "emailDomain", e.getMessage()));
            setBindingError(bindingResult, model);

            return "email/email_input";
        }

        redirectAttributes.addAttribute(ParamEnv.EMAIL.getKey(), cryptography.encrypt(email));
        return "redirect:/v1/web/email/cred";
    }

    @ResponseBody
    @PostMapping("/reissue")
    public void reissue(@Valid @RequestBody EmailRequestDto requestDto, @Invitation InvitationInfo invitationInfo) {
        collegeEmailService.verifyDomain(requestDto.emailDomain(), invitationInfo.memberId());
        emailCredentialService.issue(requestDto.getEmail());
    }

    @GetMapping("/cred")
    public String emailValidate(@EmailValue EmailInfo email, Model model) {
        EmailCredentialRequestDto emailCredentialDto = new EmailCredentialRequestDto(email.getEmailName(), email.getEmailDomain(), null);
        model.addAttribute("emailCredentialDto", emailCredentialDto);
        model.addAttribute("bindingResult", new BeanPropertyBindingResult(emailCredentialDto, "emailDto"));

        return "email/email_validate";
    }

    @PostMapping("/cred")
    public String credentialValidate(@Valid @ModelAttribute EmailCredentialRequestDto requestDto, BindingResult bindingResult,
        @Invitation InvitationInfo invitationInfo, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("emailCredentialDto", requestDto);
            setBindingError(bindingResult, model);

            return "email/email_validate";
        }
        final String email = requestDto.getEmail();

        try {
            emailCredentialService.verify(email, requestDto.credential());
            collegeEmailService.saveEmail(email, invitationInfo.memberId());
        } catch (CustomException e) {
            model.addAttribute("emailCredentialDto", requestDto);
            bindingResult.addError(new FieldError("credential", "credential", e.getMessage()));
            setBindingError(bindingResult, model);

            return "email/email_validate";
        }

        return "redirect:/v1/web/profile";
    }

    private void setBindingError(BindingResult bindingResult, Model model) {
        log.info("binding result has errors: {}", bindingResult);
        model.addAttribute("bindingResult", bindingResult);
    }
}
