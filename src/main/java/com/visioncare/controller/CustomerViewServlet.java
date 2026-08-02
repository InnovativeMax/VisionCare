package com.visioncare.controller;

import com.visioncare.common.BaseServlet;
import com.visioncare.model.Customer;
import com.visioncare.service.CustomerService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * ==========================================================
 * VisionCare ERP
 * File        : CustomerViewServlet.java
 * Description : View Customer Controller
 * Version     : 1.0
 * ==========================================================
 */
@WebServlet("/customers/view")
public class CustomerViewServlet extends BaseServlet {

    private final CustomerService customerService =
            new CustomerService();

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        Long id = Long.parseLong(
                request.getParameter("id")
        );

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

        request.setAttribute(
                "activeMenu",
                "customers"
        );

        request.setAttribute(
                "customer",
                customer
        );

        forward(
                request,
                response,
                "/customer/customer-view.jsp"
        );

    }

}