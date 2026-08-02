package com.visioncare.common;

import com.visioncare.constants.ApplicationConstants;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * ==========================================================
 * VisionCare ERP
 * File        : BaseServlet.java
 * Description : Common Servlet Utilities
 * Version     : 1.0
 * ==========================================================
 */
public abstract class BaseServlet extends HttpServlet {

    /*
     * ==========================================================
     * Forward Request
     * ==========================================================
     */

    protected void forward(
            HttpServletRequest request,
            HttpServletResponse response,
            String page)
            throws ServletException, IOException {

        request.getRequestDispatcher(page)
                .forward(request, response);

    }

    /*
     * ==========================================================
     * Redirect
     * ==========================================================
     */

    protected void redirect(
            HttpServletRequest request,
            HttpServletResponse response,
            String url)
            throws IOException {

        response.sendRedirect(
                request.getContextPath() + url
        );

    }

    /*
     * ==========================================================
     * Success Toast
     * ==========================================================
     */

    protected void success(
            HttpServletRequest request,
            String message) {

        request.getSession()
                .setAttribute(
                        ApplicationConstants.SUCCESS_MESSAGE,
                        message
                );

    }

    /*
     * ==========================================================
     * Error Toast
     * ==========================================================
     */

    protected void error(
            HttpServletRequest request,
            String message) {

        request.getSession()
                .setAttribute(
                        ApplicationConstants.ERROR_MESSAGE,
                        message
                );

    }

}