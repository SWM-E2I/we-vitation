package com.e2i.wemeet.web.dto.register;

import com.e2i.wemeet.web.domain.member.Mbti;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

public record RegisterAdditionalRequestDto (
    @NotNull
    Mbti mbti,
    String hobby,
    @Length(max = 100)
    String introduction
) {
}
