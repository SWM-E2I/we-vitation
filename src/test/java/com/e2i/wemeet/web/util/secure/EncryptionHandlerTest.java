package com.e2i.wemeet.web.util.secure;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class EncryptionHandlerTest {

    @Autowired
    private Cryptography encryptionHandler;

    @DisplayName("암호화에 성공한다.")
    @ValueSource(strings = {"+821077229911", "+821077229912", "+821077229913", "+821077229914", "+821077229915"})
    @ParameterizedTest
    void encrypt(String phone) throws Exception {
        String encrypted = encryptionHandler.encrypt(phone);

        assertThat(encrypted).isNotEqualTo(phone);
    }

    @DisplayName("복호화에 성공한다.")
    @ValueSource(strings = {"+821077229911", "+821077229912", "+821077229913", "+821077229914", "+821077229915"})
    @ParameterizedTest
    void decrypt(String phone) throws Exception {
        //given
        String encrypted = encryptionHandler.encrypt(phone);

        //when
        String decrypt = encryptionHandler.decrypt(encrypted);

        //then
        assertThat(decrypt).isNotEqualTo(encrypted);
        assertThat(decrypt).isEqualTo(phone);
    }
}