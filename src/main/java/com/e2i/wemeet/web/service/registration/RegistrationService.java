package com.e2i.wemeet.web.service.registration;

import com.e2i.wemeet.web.domain.member.Member;
import com.e2i.wemeet.web.domain.member.MemberRepository;
import com.e2i.wemeet.web.dto.register.MemberRequestDetails;
import com.e2i.wemeet.web.dto.register.RegisterAdditionalRequestDto;
import com.e2i.wemeet.web.dto.register.RegisterBasicRequestDto;
import com.e2i.wemeet.web.exception.internal.InternalServerException;
import com.e2i.wemeet.web.util.serialize.SerializeUtils;
import java.io.IOException;
import java.time.Duration;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

@Slf4j
@Transactional
@RequiredArgsConstructor
@Service
public class RegistrationService {

    private final RedisTemplate<String, String> redisTemplate;
    private final MemberRepository memberRepository;

    public void saveRegistration(final RegisterBasicRequestDto requestDto, final String key, final String phoneNumber) {
        MemberRequestDetails memberRequestDetails = new MemberRequestDetails();
        memberRequestDetails.setBasicInformation(requestDto, phoneNumber);

        saveMemberDetail(memberRequestDetails, key);
        log.info("RegistrationService basic save ::  memberRequestDetails: {}", memberRequestDetails);
    }

    public Long saveRegistration(final RegisterAdditionalRequestDto requestDto, final String key) {
        MemberRequestDetails memberRequestDetails = findMemberDetail(key);
        if (memberRequestDetails == null) {
            return null;
        }

        memberRequestDetails.setAdditionalInformation(requestDto);

        Member member = saveMember(memberRequestDetails);
        log.info("RegistrationService additional save ::  memberRequestDetails: {}", memberRequestDetails);

        return member.getMemberId();
    }

    private MemberRequestDetails findMemberDetail(final String key) {
        ValueOperations<String, String> operations = redisTemplate.opsForValue();
        String serializedMember = operations.get(key);

        if (!StringUtils.hasText(serializedMember)) {
            return null;
        }
        return SerializeUtils.deserialize(serializedMember, MemberRequestDetails.class);
    }

    private void saveMemberDetail(final MemberRequestDetails memberRequestDetails, final String key) {
        ValueOperations<String, String> operations = redisTemplate.opsForValue();

        String serializedMember = SerializeUtils.serialize(memberRequestDetails);
        operations.set(key, serializedMember, Duration.ofHours(12));
    }

    public Member saveMember(MemberRequestDetails memberRequestDetails) {
        return memberRepository.findByPhoneNumber(memberRequestDetails.getPhoneNumber())
            .orElseGet(() -> {
                log.info("SAVE MEMBER :: Details: {}", memberRequestDetails);
                return memberRepository.save(memberRequestDetails.toEntity());
            });
    }

}
