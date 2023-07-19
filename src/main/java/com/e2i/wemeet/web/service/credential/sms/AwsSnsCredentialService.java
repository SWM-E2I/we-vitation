package com.e2i.wemeet.web.service.credential.sms;

import com.e2i.wemeet.web.exception.badrequest.CredentialNotMatchException;
import com.e2i.wemeet.web.exception.internal.AwsSnsTransferException;
import com.e2i.wemeet.web.exception.notfound.SmsCredentialNotFoundException;
import com.e2i.wemeet.web.util.code.RandomCodeUtils;
import java.time.Duration;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import software.amazon.awssdk.services.sns.SnsClient;
import software.amazon.awssdk.services.sns.model.PublishRequest;
import software.amazon.awssdk.services.sns.model.PublishResponse;
import software.amazon.awssdk.services.sns.model.SnsException;

@Slf4j
@RequiredArgsConstructor
@Service
public class AwsSnsCredentialService implements SmsCredentialService {

    private final SnsClient snsClient;
    private final RedisTemplate<String, String> redisTemplate;

    @Transactional
    @Override
    public void issue(final String phone) {
        ValueOperations<String, String> operations = redisTemplate.opsForValue();

        String credential = RandomCodeUtils.createCredential();
        sendSms(phone, credential);

        operations.set(phone, credential, Duration.ofMinutes(3));
    }

    @Transactional
    @Override
    public void verify(final String target, final String credential) {
        if (!matches(target, credential)) {
            throw new CredentialNotMatchException();
        }

        redisTemplate.delete(target);
    }

    @Override
    public boolean matches(final String target, final String input) {
        ValueOperations<String, String> operations = redisTemplate.opsForValue();
        String origin = operations.get(target);

        if (!StringUtils.hasText(origin)) {
            throw new SmsCredentialNotFoundException();
        }

        return origin.equals(input);
    }

    private void sendSms(final String phoneNumber, final String message) {
        try {
            PublishRequest request = PublishRequest.builder()
                .message(message)
                .phoneNumber(phoneNumber)
                .build();

            PublishResponse result = snsClient.publish(request);
            log.info("Message sent. MessageId: {} / Phone: {}", result.messageId(), phoneNumber);

        } catch (SnsException e) {
            log.info(e.getMessage());
            throw new AwsSnsTransferException();
        }
    }
}
