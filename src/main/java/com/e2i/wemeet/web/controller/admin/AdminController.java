package com.e2i.wemeet.web.controller.admin;

import com.e2i.wemeet.web.domain.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Profile("!prod")
@RequiredArgsConstructor
@RequestMapping("/admin")
@RestController
public class AdminController {

    private final MemberRepository memberRepository;

    @Transactional
    @GetMapping("/delete/{phoneNumber}")
    public String deleteMember(@PathVariable String phoneNumber) {
        String phone = phoneNumber.replaceFirst("0", "+82");
        memberRepository.deleteByPhoneNumber(phone);
        return "DELETE MEMBER SUCCESS :: " + phoneNumber;
    }

    @Transactional
    @GetMapping("/delete/all")
    public String deleteAllMember() {
        memberRepository.deleteAllNewMember();
        return "DELETE ALL MEMBER SUCCESS";
    }
}
