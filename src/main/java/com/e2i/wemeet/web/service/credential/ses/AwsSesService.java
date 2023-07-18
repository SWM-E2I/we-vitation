package com.e2i.wemeet.web.service.credential.ses;

import com.e2i.wemeet.web.domain.member.MemberRepository;
import com.e2i.wemeet.web.exception.badrequest.CredentialNotMatchException;
import com.e2i.wemeet.web.exception.internal.AwsSesTransferException;
import com.e2i.wemeet.web.exception.internal.MailParseException;
import com.e2i.wemeet.web.exception.notfound.EmailCredentialNotFoundException;
import com.e2i.wemeet.web.util.code.RandomCodeUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import software.amazon.awssdk.services.ses.SesClient;
import software.amazon.awssdk.services.ses.model.SendTemplatedEmailRequest;
import software.amazon.awssdk.services.ses.model.SendTemplatedEmailResponse;
import software.amazon.awssdk.services.ses.model.SesException;

@Slf4j
@RequiredArgsConstructor
@Service
public class AwsSesService implements EmailCredentialService {
    private static final String TEMPLATE_NAME = "certifyEmailTemplate";
    private static final String SOURCE_EMAIL = "qkrdbsk28@naver.com";

    private final SesClient sesClient;
    private final RedisTemplate<String, String> redisTemplate;

    @Transactional
    @Override
    public void issue(String receiveTarget) {
        ValueOperations<String, String> operations = redisTemplate.opsForValue();

        String credential = RandomCodeUtils.createCredential();
        sendEmail(receiveTarget, credential);

        operations.set(receiveTarget, credential, Duration.ofMinutes(10));
    }

    @Transactional
    @Override
    public void verify(final String target, final String credential, final Long memberId) {
        if (!matches(target, credential)) {
            throw new CredentialNotMatchException();
        }
    }

    @Override
    public boolean matches(final String target, final String input) {
        ValueOperations<String, String> operations = redisTemplate.opsForValue();
        String origin = operations.get(target);
        if (!StringUtils.hasText(origin)) {
            throw new EmailCredentialNotFoundException();
        }

        return origin.equals(input);
    }


    private void sendEmail(String email, String message) {
        SendTemplatedEmailRequest emailRequest = createEmailRequest(email, message);

        try {
            SendTemplatedEmailResponse result = sesClient.sendTemplatedEmail(emailRequest);
            log.info("Email sent. EmailId: " + result.messageId());
        } catch (SesException e) {
            log.info(e.getMessage());
            throw new AwsSesTransferException();
        }
    }

    private SendTemplatedEmailRequest createEmailRequest(String email, String message) {
        try {
            return SendTemplatedEmailRequest.builder()
                .source(SOURCE_EMAIL)
                .destination(d -> d.toAddresses(email))
                .template(TEMPLATE_NAME)
                .templateData(this.toJson(message))
                .build();
        } catch (JsonProcessingException e) {
            throw new MailParseException();
        }
    }

    private String toJson(String message) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();

        Map<String, String> templateData = new HashMap<>();
        templateData.put("code", message);

        return mapper.writeValueAsString(templateData);
    }
}
