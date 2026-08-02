package com.visioncare.controller;

import com.visioncare.common.BaseServlet;
import com.visioncare.service.CustomerService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.visioncare.model.Customer;

import java.io.IOException;

/**
 * ==========================================================
 * VisionCare ERP
 * File        : CustomerDeleteServlet.java
 * Description : Customer Soft Delete Controller
 * Version     : 1.0
 * ==========================================================
 */
@WebServlet("/customers/delete")
public class CustomerDeleteServlet extends BaseServlet {

    private final CustomerService customerService =
            new CustomerService();

    /*
     * ==========================================================
     * Soft Delete Customer
     * ==========================================================
     */

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        Long id = Long.parseLong(
                request.getParameter("id")
        );

        /*
         * ==========================================================
         * Validate Customer
         * ==========================================================
         */

        Customer customer =
                customerService.getCustomerById(id);

        if (customer == null) {

            error(
                    request,
                    "Customer not found."
            );

            redirect(
                    request,
                    response,
                    "/customers"
            );

            return;

        }

        /*
         * ==========================================================
         * Deactivate Customer
         * ==========================================================
         */

        customerService.deactivateCustomer(id);

        /*
         * ==========================================================
         * Success Message & Redirect
         * ==========================================================
         */

        success(
                request,
                "Customer deactivated successfully."
        );

        redirect(
                request,
                response,
                "/customers"
        );

    }

}