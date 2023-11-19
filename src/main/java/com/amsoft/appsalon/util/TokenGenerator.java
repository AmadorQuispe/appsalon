package com.amsoft.appsalon.util;

import java.util.UUID;
import java.security.SecureRandom;
import java.util.Base64;

public class TokenGenerator {
    private static final int TOKEN_LENGTH = 36;

    public static String generateToken() {
        UUID uuid = UUID.randomUUID();
        String baseToken = uuid.toString().replace("-", "");

        SecureRandom random = new SecureRandom();
        byte[] randomBytes = new byte[TOKEN_LENGTH - baseToken.length()];
        random.nextBytes(randomBytes);
        String randomToken = Base64.getUrlEncoder().withoutPadding().encodeToString(randomBytes);

        return baseToken + randomToken;
    }

}
