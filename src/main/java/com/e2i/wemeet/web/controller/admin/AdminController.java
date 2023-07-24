package com.e2i.wemeet.web.controller.admin;

import com.e2i.wemeet.web.domain.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@Profile("default")
@RequiredArgsConstructor
@RestController("/admin")
public class AdminController {

    private final MemberRepository memberRepository;

    @GetMapping("/delete/{phoneNumber}")
    public String deleteMember(@PathVariable String phoneNumber) {
        memberRepository.deleteByPhoneNumber(phoneNumber);
        return "DELETE MEMBER SUCCESS :: " + phoneNumber;
    }

    @GetMapping("/delete/all")
    public String deleteAllMember() {
        memberRepository.deleteAll();
        return "DELETE ALL MEMBER SUCCESS";
    }
}
