package com.e2i.wemeet.web.dto.email;

import com.e2i.wemeet.web.util.validator.bean.EmailDomain;
import jakarta.validation.constraints.NotEmpty;

public record EmailRequestDto(
    @NotEmpty
    String emailName,

    @EmailDomain
    String emailDomain
) {

    public String getEmail() {
        return this.emailName + "@" + this.emailDomain;
    }
}
