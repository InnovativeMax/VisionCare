package com.visioncare.filter;

import com.visioncare.constants.ApplicationConstants;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

/**
 * ==========================================================
 * VisionCare ERP
 * File        : AuthenticationFilter.java
 * Description : Protects secured resources
 * Version     : 1.0
 * ==========================================================
 */
@WebFilter("/dashboard/*")
public class AuthenticationFilter extends HttpFilter implements Filter {

    @Override
    protected void doFilter(HttpServletRequest request,
                            HttpServletResponse response,
                            FilterChain chain)
            throws IOException, ServletException {

        HttpSession session = request.getSession(false);

        boolean loggedIn =
                session != null &&
                        session.getAttribute(
                                ApplicationConstants.LOGGED_IN_USER
                        ) != null;

        if (loggedIn) {
            response.setHeader(
                    "Cache-Control",
                    "no-cache, no-store, must-revalidate"
            );

            response.setHeader(
                    "Pragma",
                    "no-cache"
            );

            response.setDateHeader(
                    "Expires",
                    0
            );
            chain.doFilter(request, response);

        } else {

            response.sendRedirect(
                    request.getContextPath() + "/auth/login.jsp"
            );

        }

    }

}