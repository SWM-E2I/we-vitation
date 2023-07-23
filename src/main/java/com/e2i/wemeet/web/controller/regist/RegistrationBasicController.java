package com.e2i.wemeet.web.controller.regist;

import com.e2i.wemeet.web.domain.member.AdmissionYears;
import com.e2i.wemeet.web.domain.member.CollegeTypes;
import com.e2i.wemeet.web.domain.member.Colleges;
import com.e2i.wemeet.web.domain.member.Gender;
import com.e2i.wemeet.web.dto.register.RegisterBasicRequestDto;
import com.e2i.wemeet.web.global.resolver.phone.PhoneNumberInfo;
import com.e2i.wemeet.web.global.resolver.phone.PhoneNumberValue;
import com.e2i.wemeet.web.global.resolver.teamCode.TeamCodeInfo;
import com.e2i.wemeet.web.global.resolver.teamCode.TeamCodeValue;
import com.e2i.wemeet.web.service.registration.RegistrationService;
import com.e2i.wemeet.web.service.team.TeamService;
import com.e2i.wemeet.web.util.serialize.SerializeUtils;
import jakarta.validation.Valid;
import java.util.Arrays;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/v1/web/register")
@Controller
public class RegistrationBasicController {

    private final RegistrationService registrationService;
    private final TeamService teamService;

    // Univ List
    @ModelAttribute("colleges")
    public List<String> colleges() {
        return Arrays.stream(Colleges.values())
            .map(Colleges::getCollegeName)
            .toList();
    }

    // Univ Type List
    @ModelAttribute("collegeTypes")
    public List<String> collegeTypes() {
        return Arrays.stream(CollegeTypes.values())
            .map(CollegeTypes::getCollegeTypeName)
            .toList();
    }

    // Admission Year List
    @ModelAttribute("admissionYears")
    public List<String> admissionYears() {
        return Arrays.stream(AdmissionYears.values())
            .map(AdmissionYears::getYear)
            .toList();
    }


    @GetMapping
    public String registerBasic(@TeamCodeValue TeamCodeInfo teamCodeInfo, Model model) {
        Gender teamGender = teamService.getTeamGender(teamCodeInfo.teamCode());
        RegisterBasicRequestDto requestDto = RegisterBasicRequestDto.getEmptyInstance(teamGender);

        model.addAttribute("registerDto", requestDto);
        model.addAttribute("bindingResult", new BeanPropertyBindingResult(requestDto, "registerDto"));

        return "register/register_basic";
    }

    @PostMapping
    public String registerBasicPost(@Valid @ModelAttribute RegisterBasicRequestDto registerBasicRequestDto,
                                    BindingResult bindingResult,
                                    @PhoneNumberValue PhoneNumberInfo phoneNumberInfo,
                                    @TeamCodeValue TeamCodeInfo teamCodeInfo, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("registerDto", registerBasicRequestDto);
            setBindingError(bindingResult, model);

            return "register/register_basic";
        }

        try {
            final String key = SerializeUtils.getMemberDetailKey(teamCodeInfo.teamCode(), phoneNumberInfo.phoneNumber());
            registrationService.saveRegistration(registerBasicRequestDto, key, phoneNumberInfo.phoneNumber());

            return "redirect:/v1/web/register/additional";
        } catch (Exception e) {
            model.addAttribute("registerDto", registerBasicRequestDto);
            bindingResult.reject("global", "요청을 수행하는 중에 오류가 발생했습니다. 다시 시도해주세요");
            setBindingError(bindingResult, model);

            return "register/register_basic";
        }
    }

    private void setBindingError(BindingResult bindingResult, Model model) {
        log.info("binding result has errors: {}", bindingResult);
        model.addAttribute("bindingResult", bindingResult);
    }
}
