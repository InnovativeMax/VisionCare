package com.visioncare.util;

import org.mindrot.jbcrypt.BCrypt;

/**
 * Utility class for password hashing and verification.
 */
public final class PasswordUtil {

    private PasswordUtil() {
        // Prevent object creation
    }

    /**
     * Hash a plain text password.
     */
    public static String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    /**
     * Verify password against stored hash.
     */
    public static boolean verifyPassword(String password, String passwordHash) {
        return BCrypt.checkpw(password, passwordHash);
    }

}