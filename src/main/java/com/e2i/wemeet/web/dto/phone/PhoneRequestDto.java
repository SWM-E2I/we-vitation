package com.e2i.wemeet.web.dto.phone;

import com.e2i.wemeet.web.util.validator.Phone;

public record PhoneRequestDto (
    @Phone
    String phoneNumber

) {
}
