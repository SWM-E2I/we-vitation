package com.e2i.wemeet.web.global.resolver.invitation;

import com.e2i.wemeet.web.global.env.CookieEnv;

public record InvitationInfo(String teamCode, Long memberId) {
    public static InvitationInfo of(String identifier) {
        String[] identifierArr = identifier.split(CookieEnv.PERSONAL_IDENTIFIER.getDelimiter());

        return new InvitationInfo(identifierArr[0], Long.parseLong(identifierArr[1]));
    }
}
