package com.e2i.wemeet.web.dto.email;

import com.e2i.wemeet.web.util.validator.bean.EmailDomain;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public record EmailCredentialRequestDto (
    @NotBlank
    String emailName,

    @EmailDomain
    String emailDomain,

    @Length(min = 6, max = 6, message = "{Length.CredentialRequestDto.credential}")
    String credential
) {

}
