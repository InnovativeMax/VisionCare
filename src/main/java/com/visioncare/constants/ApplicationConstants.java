package com.visioncare.constants;

/**
 * ==========================================================
 * VisionCare ERP
 * File        : ApplicationConstants.java
 * Description : Application-wide constants
 * Version     : 1.0
 * ==========================================================
 */
public final class ApplicationConstants {

    /*
     * ==========================================================
     * Prevent Object Creation
     * ==========================================================
     */

    private ApplicationConstants() {

    }

    /*
     * ==========================================================
     * Session
     * ==========================================================
     */

    public static final String LOGGED_IN_USER = "loggedInUser";

    public static final int SESSION_TIMEOUT = 30 * 60;

    public static final String SUCCESS_MESSAGE =
            "successMessage";

    public static final String ERROR_MESSAGE =
            "errorMessage";

    public static final String WARNING_MESSAGE =
            "warningMessage";

    public static final String INFO_MESSAGE =
            "infoMessage";

}