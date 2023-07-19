package com.e2i.wemeet.web.global.resolver;

public record InvitationInfo(String teamCode, Long memberId) {
    public static InvitationInfo of(String identifier) {
        String[] identifierArr = identifier.split("-");

        return new InvitationInfo(identifierArr[0], Long.parseLong(identifierArr[1]));
    }
}
