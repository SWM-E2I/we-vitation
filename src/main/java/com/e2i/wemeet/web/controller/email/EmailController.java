package com.e2i.wemeet.web.controller.email;

import com.e2i.wemeet.web.domain.member.Colleges;
import com.e2i.wemeet.web.dto.email.EmailRequestDto;
import com.e2i.wemeet.web.exception.CustomException;
import com.e2i.wemeet.web.global.resolver.Invitation;
import com.e2i.wemeet.web.global.resolver.InvitationInfo;
import com.e2i.wemeet.web.service.credential.ses.EmailCredentialService;
import com.e2i.wemeet.web.service.email.CollegeEmailService;
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
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/v1/web/email")
@Controller
public class EmailController {

    private final EmailCredentialService emailCredentialService;
    private final CollegeEmailService collegeEmailService;

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
            Model model, @Invitation InvitationInfo invitationInfo) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("emailDto", emailDto);
            setBindingError(bindingResult, model);

            return "email/email_input";
        }
        final String email = getEmail(emailDto);

        try {
            collegeEmailService.verifyDomain(emailDto.emailDomain(), invitationInfo.memberId());
            emailCredentialService.issue(email);
        } catch (CustomException e) {
            model.addAttribute("emailDto", emailDto);
            bindingResult.addError(new FieldError("emailDomain", "emailDomain", e.getMessage()));
            setBindingError(bindingResult, model);

            return "email/email_input";
        }

        return "redirect:/v1/web/email/validate";
    }

    private String getEmail(EmailRequestDto emailDto) {
        return emailDto.emailName() + "@" + emailDto.emailDomain();
    }

    @GetMapping("/validate")
    public String emailValidate() {
        return "email/email_validate";
    }

    @PostMapping("/validate")
    public void credentialValidate(@Invitation InvitationInfo invitationInfo) {
        System.out.println(invitationInfo);
    }

    private void setBindingError(BindingResult bindingResult, Model model) {
        log.info("binding result has errors: {}", bindingResult);
        model.addAttribute("bindingResult", bindingResult);
    }
}
