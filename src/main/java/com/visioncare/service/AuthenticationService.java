package com.visioncare.service;

import com.visioncare.dao.UserDAO;
import com.visioncare.model.User;
import org.mindrot.jbcrypt.BCrypt;

/**
 * ==========================================================
 * VisionCare ERP
 * File        : AuthenticationService.java
 * Description : Authentication Business Logic
 * Version     : 1.0
 * ==========================================================
 */
public class AuthenticationService {

    private final UserDAO userDAO = new UserDAO();

    /*
     * ==========================================================
     * Authenticate User
     * ==========================================================
     */

    public User authenticate(String username, String password) {

        User user = userDAO.findByUsername(username);

        if (user == null) {
            return null;
        }

        boolean passwordMatches =
                BCrypt.checkpw(password, user.getPassword());

        if (!passwordMatches) {
            return null;
        }

        userDAO.updateLastLogin(user.getId());

        return user;

    }

}