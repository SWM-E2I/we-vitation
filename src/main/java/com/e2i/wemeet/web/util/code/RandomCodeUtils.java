package com.e2i.wemeet.web.util.code;

import com.e2i.wemeet.web.domain.member.Member;
import java.security.SecureRandom;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class RandomCodeUtils {

    private static final SecureRandom random = new SecureRandom();
    private static final int TEAMCODE_LENGTH = 6;

    private RandomCodeUtils() {
    }

    public static String createCredential() {
        int code = random.nextInt(900_000) + 100_000;
        return String.valueOf(code);
    }

    public static String createIntegerCode4() {
        int code = random.nextInt(9000) + 1000;
        return String.valueOf(code);
    }

    public static String createTeamCode(final Member teamLeader) {
        String alphanumeric = "abcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder sb = new StringBuilder(TEAMCODE_LENGTH);

        for (int i = 0; i < TEAMCODE_LENGTH; i++) {
            int index = random.nextInt(alphanumeric.length());
            sb.append(alphanumeric.charAt(index));
        }
        String teamCode = teamLeader.getMemberId() + "@" + sb.toString();

        log.info("GENERATED:: memberId: {}, teamCode: {}", teamLeader.getMemberId(), teamCode);
        return teamCode;
    }
}
