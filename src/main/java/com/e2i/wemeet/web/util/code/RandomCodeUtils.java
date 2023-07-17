package com.e2i.wemeet.web.util.code;

import java.security.SecureRandom;

public abstract class RandomCodeUtils {

    private static final SecureRandom random = new SecureRandom();

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
}
