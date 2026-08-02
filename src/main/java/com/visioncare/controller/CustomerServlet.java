package com.visioncare.controller;

import com.visioncare.common.BaseServlet;
import com.visioncare.model.Customer;
import com.visioncare.service.CustomerService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * ==========================================================
 * VisionCare ERP
 * File        : CustomerServlet.java
 * Description : Customer Controller
 * Version     : 1.0
 * ==========================================================
 */
@WebServlet("/customers")
public class CustomerServlet extends BaseServlet {

    /*
     * ==========================================================
     * Dependencies
     * ==========================================================
     */
    private static final int PAGE_SIZE = 10;

    private final CustomerService customerService =
            new CustomerService();

    /*
     * ==========================================================
     * Display Customer List
     * ==========================================================
     */

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        /*
         * ==========================================================
         * Pagination
         * ==========================================================
         */

        int page = 1;

        String pageParameter =
                request.getParameter("page");

        if (pageParameter != null &&
                !pageParameter.isBlank()) {

            try {

                page = Integer.parseInt(pageParameter);

                if (page < 1) {

                    page = 1;

                }

            } catch (NumberFormatException exception) {

                page = 1;

            }

        }

        int offset =
                (page - 1) * PAGE_SIZE;

        String status =
                request.getParameter("status");

        if (status == null || status.isBlank()) {

            status = "active";

        }

        String keyword =
                request.getParameter("keyword");

        if (keyword != null) {

            keyword = keyword.trim();

        }

        /*
         * ==========================================================
         * Count Total Customers
         * ==========================================================
         */

        int totalRecords;

        if (keyword != null && !keyword.isBlank()) {

            totalRecords =
                    customerService.countSearchResults(
                            keyword,
                            status
                    );

        } else {

            totalRecords =
                    customerService.countCustomersByStatus(
                            status
                    );

        }

        int totalPages =
                (int) Math.ceil(
                        (double) totalRecords / PAGE_SIZE
                );

        /*
         * ==========================================================
         * Validate Current Page
         * ==========================================================
         */

        if (totalPages > 0 && page > totalPages) {

            page = totalPages;

            offset = (page - 1) * PAGE_SIZE;

        }

        List<Customer> customers;

        if (keyword != null &&
                !keyword.isBlank()) {

            customers =
                    customerService.searchCustomers(
                            keyword,
                            status,
                            offset,
                            PAGE_SIZE
                    );

        } else {

            customers =
                    customerService.getCustomersByStatus(
                            status,
                            offset,
                            PAGE_SIZE
                    );

        }

        String paginationQuery =
                "&status=" + status +
                        "&keyword=" + URLEncoder.encode(
                        keyword == null ? "" : keyword,
                        StandardCharsets.UTF_8
                );

        request.setAttribute("status", status);

        request.setAttribute("keyword", keyword);

        request.setAttribute("customers", customers);

        request.setAttribute("currentPage", page);

        request.setAttribute("totalPages", totalPages);

        request.setAttribute("totalRecords", totalRecords);

        request.setAttribute("pageSize", PAGE_SIZE);

        request.setAttribute("paginationQuery", paginationQuery);

        request.setAttribute("activeMenu", "customers");
        request.setAttribute("activeSection", "masters");

        request.getRequestDispatcher("/customer/customers.jsp")
                .forward(request, response);

    }

}