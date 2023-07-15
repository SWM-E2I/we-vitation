package com.e2i.wemeet.web.domain.base;

import com.e2i.wemeet.web.util.secure.Cryptography;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import org.springframework.stereotype.Component;

@Converter
@Component
public class CryptoConverter implements AttributeConverter<String, String> {
    private final Cryptography cryptography;

    public CryptoConverter(Cryptography cryptography) {
        this.cryptography = cryptography;
    }

    // DB에 저장될 때 (암호화)
    @Override
    public String convertToDatabaseColumn(String attribute) {
        if (attribute == null) {
            return null;
        }
        return cryptography.encrypt(attribute);
    }

    // DB에서 읽어올 때 (복호화)
    @Override
    public String convertToEntityAttribute(String dbData) {
        if (dbData == null) {
            return null;
        }
        return cryptography.decrypt(dbData);
    }
}
