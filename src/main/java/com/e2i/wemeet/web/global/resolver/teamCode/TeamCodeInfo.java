package com.e2i.wemeet.web.global.resolver.teamCode;

public record TeamCodeInfo(String teamCode) {
    public String getLeaderMemberId() {
        return this.teamCode.split("@")[0];
    }
}
