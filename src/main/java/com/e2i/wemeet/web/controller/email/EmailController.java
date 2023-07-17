package com.e2i.wemeet.web.controller.email;

import com.e2i.wemeet.web.global.resolver.Invitation;
import com.e2i.wemeet.web.global.resolver.InvitationInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/v1/web/email")
@Controller
public class EmailController {

    @GetMapping
    public String email(@Invitation InvitationInfo invitationInfo) {
        System.out.println("invitationInfo teamCode = " + invitationInfo.teamCode());
        System.out.println("invitationInfo memberId = " + invitationInfo.memberId());

        return "email/email_input";
    }

    @PostMapping
    public String issue() {
        return "redirect:/";
    }

    @GetMapping("/validate")
    public String emailValidate() {
        return "email/email_validate";
    }

    @PostMapping("/validate")
    public void credentialValidate(@Invitation InvitationInfo invitationInfo) {
        System.out.println(invitationInfo);
    }
}
