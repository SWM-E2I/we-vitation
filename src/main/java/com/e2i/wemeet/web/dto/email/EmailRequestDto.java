package com.e2i.wemeet.web.dto.email;

import com.e2i.wemeet.web.util.validator.bean.EmailDomain;

public record EmailRequestDto(
    String emailName,

    @EmailDomain
    String emailDomain
) {
}
