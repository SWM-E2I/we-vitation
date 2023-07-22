package com.e2i.wemeet.web.controller.profile;

import com.e2i.wemeet.web.exception.CustomException;
import com.e2i.wemeet.web.global.resolver.invitation.Invitation;
import com.e2i.wemeet.web.global.resolver.invitation.InvitationInfo;
import com.e2i.wemeet.web.service.profile.ProfileImageService;
import com.e2i.wemeet.web.service.team.TeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@RequestMapping("/v1/web/profile")
@Controller
public class ProfileController {

    private final ProfileImageService profileService;
    private final TeamService teamService;

    @GetMapping
    public String profile(Model model) {
        model.addAttribute("exception", null);
        return "profile/profile";
    }

    @PostMapping
    public String profileUpload(@RequestParam(name = "profile") MultipartFile multipartFile,
            @Invitation InvitationInfo invitationInfo,
            Model model) {
        try {
            profileService.postProfileImage(invitationInfo.memberId(), multipartFile, true);
            teamService.registerTeam(invitationInfo.memberId(), invitationInfo.teamCode());
            return "redirect:/v1/web/finish";
        } catch (CustomException e) {
            model.addAttribute("exception", e.getMessage());
            return "profile/profile";
        }
    }
}
