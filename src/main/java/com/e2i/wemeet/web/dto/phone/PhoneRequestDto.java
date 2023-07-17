package com.e2i.wemeet.web.dto.phone;

import com.e2i.wemeet.web.util.validator.bean.Phone;

public record PhoneRequestDto (
    @Phone
    String phone

) {
}
