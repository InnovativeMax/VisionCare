package com.visioncare.controller;

import com.visioncare.model.User;
import com.visioncare.service.AuthenticationService;

import com.visioncare.constants.ApplicationConstants;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

/**
 * ==========================================================
 * VisionCare ERP
 * File        : LoginServlet.java
 * Description : Login Controller
 * Version     : 1.0
 * ==========================================================
 */
@WebServlet("/auth/login")
public class LoginServlet extends HttpServlet {

    private static final int SESSION_TIMEOUT = 30 * 60;
    private final AuthenticationService authenticationService =
            new AuthenticationService();

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("===== LOGIN SERVLET VERSION 2 =====");
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        User user =
                authenticationService.authenticate(username, password);

        if (user != null) {

            HttpSession session = request.getSession(true);
            session.setMaxInactiveInterval(
                    ApplicationConstants.SESSION_TIMEOUT
            );

            session.setAttribute(
                    ApplicationConstants.LOGGED_IN_USER,
                    user
            );

            /*
             * TODO:
             * Replace with DashboardServlet once implemented.
             */
// response.sendRedirect(
//         request.getContextPath() + "/dashboard"
// );

            response.sendRedirect(
                    request.getContextPath() + "/dashboard/dashboard.jsp"
            );

        } else {

            request.setAttribute(
                    "error",
                    "Invalid username or password."
            );

            request.getRequestDispatcher("/auth/login.jsp")
                    .forward(request, response);

        }

    }

}