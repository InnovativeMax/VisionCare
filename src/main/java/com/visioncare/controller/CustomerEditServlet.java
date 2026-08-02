package com.visioncare.controller;

import com.visioncare.common.BaseServlet;
import com.visioncare.model.Customer;
import com.visioncare.service.CustomerService;
import com.visioncare.validator.CustomerValidator;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@WebServlet("/customers/edit")
public class CustomerEditServlet extends BaseServlet {

    private final CustomerService customerService =
            new CustomerService();

    private final CustomerValidator customerValidator =
            new CustomerValidator();

    /*
     * ==========================================================
     * Display Edit Customer Form
     * ==========================================================
     */

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
                "customer",
                customer
        );

        request.setAttribute(
                "activeMenu",
                "customers"
        );

        forward(
                request,
                response,
                "/customer/customer-form.jsp"
        );

    }

    /*
     * ==========================================================
     * Update Customer
     * ==========================================================
     */

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        Customer customer = new Customer();

        customer.setId(
                Long.parseLong(
                        request.getParameter("id")
                )
        );

        customer.setFullName(
                request.getParameter("fullName")
        );

        customer.setMobileNumber(
                request.getParameter("mobileNumber")
        );

        customer.setEmail(
                request.getParameter("email")
        );

        String gender =
                request.getParameter("gender");

        customer.setGender(
                (gender == null || gender.isBlank())
                        ? null
                        : gender
        );

        customer.setAddressLine1(
                request.getParameter("addressLine1")
        );

        customer.setAddressLine2(
                request.getParameter("addressLine2")
        );

        customer.setCity(
                request.getParameter("city")
        );

        customer.setState(
                request.getParameter("state")
        );

        customer.setPincode(
                request.getParameter("pincode")
        );

        customer.setNotes(
                request.getParameter("notes")
        );

        String dateOfBirth =
                request.getParameter("dateOfBirth");

        if (dateOfBirth != null &&
                !dateOfBirth.isBlank()) {

            customer.setDateOfBirth(
                    LocalDate.parse(dateOfBirth)
            );

        }

        /*
         * ==========================================================
         * Validate Customer
         * ==========================================================
         */

        List<String> validationErrors =
                customerValidator.validate(customer);
        if (!validationErrors.isEmpty()) {

            request.setAttribute(
                    "validationErrors",
                    validationErrors
            );

            request.setAttribute(
                    "customer",
                    customer
            );

            request.setAttribute(
                    "activeMenu",
                    "customers"
            );

            forward(
                    request,
                    response,
                    "/customer/customer-form.jsp"
            );

            return;

        }
        /*
         * ==========================================================
         * Update Customer
         * ==========================================================
         */
        customerService.updateCustomer(customer);

        success(
                request,
                "Customer updated successfully."
        );

        redirect(
                request,
                response,
                "/customers/view?id=" + customer.getId()
        );

    }

}