package com.e2i.wemeet.web.dto.phone;

import com.e2i.wemeet.web.util.validator.bean.Phone;
import org.hibernate.validator.constraints.Length;

public record PhoneCredentialRequestDto(
    @Phone
    String phone,
    @Length(min = 6, max = 6, message = "{Length.CredentialRequestDto.credential}")
    String credential
) {
    public String getPrefixedPhoneNumber() {
        return this.phone.replaceFirst("0", "+82");
    }
}
