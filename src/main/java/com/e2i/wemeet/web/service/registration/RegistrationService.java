package com.e2i.wemeet.web.service.registration;

import com.e2i.wemeet.web.domain.member.Member;
import com.e2i.wemeet.web.domain.member.MemberRepository;
import com.e2i.wemeet.web.dto.register.MemberRequestDetails;
import com.e2i.wemeet.web.dto.register.RegisterAdditionalRequestDto;
import com.e2i.wemeet.web.dto.register.RegisterBasicRequestDto;
import com.e2i.wemeet.web.exception.internal.InternalServerException;
import com.e2i.wemeet.web.util.serialize.SerializeUtils;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        memberRequestDetails.setAdditionalInformation(requestDto);

        log.info("RegistrationService additional save ::  memberRequestDetails: {}", memberRequestDetails);
        Member member = saveMember(memberRequestDetails);

        return member.getMemberId();
    }

    private MemberRequestDetails findMemberDetail(final String key) {
        ValueOperations<String, String> operations = redisTemplate.opsForValue();
        String serializedMember = operations.get(key);

        try {
            return SerializeUtils.deserialize(serializedMember, MemberRequestDetails.class);
        } catch (IOException ioException) {
            log.info("SerializeUtils.deserialize() - Exception: {}", ioException.getMessage());
            throw new InternalServerException("역직렬화에 실패했습니다.");
        } catch (Exception e) {
            log.info("InternalServerException - Exception: {}", e.getMessage());
            throw new InternalServerException("입력 정보를 불러오는 도중 문제가 발생했습니다.");
        }
    }

    private void saveMemberDetail(final MemberRequestDetails memberRequestDetails, final String key) {
        ValueOperations<String, String> operations = redisTemplate.opsForValue();

        try {
            String serializedMember = SerializeUtils.serialize(memberRequestDetails);
            operations.set(key, serializedMember);
        } catch (IOException ioException) {
            log.info("SerializeUtils.serialize() - Exception: {}", ioException.getMessage());
            throw new InternalServerException("직렬화에 실패했습니다.");
        } catch (Exception e) {
            log.info("InternalServerException - Exception: {}", e.getMessage());
            throw new InternalServerException("입력 정보를 저장하는 도중 문제가 발생했습니다.");
        }
    }

    public Member saveMember(MemberRequestDetails memberRequestDetails) {
        return memberRepository.save(memberRequestDetails.toEntity());
    }

}
