package com.visioncare.controller;

import com.visioncare.dao.BillDAO;
import com.visioncare.dao.CustomerDAO;
import com.visioncare.dao.ProductDAO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/dashboard")
public class DashboardServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        CustomerDAO customerDAO = new CustomerDAO();
        ProductDAO productDAO = new ProductDAO();
        BillDAO billDAO = new BillDAO();

        request.setAttribute(
                "customerCount",
                customerDAO.countByStatus("active"));

        request.setAttribute(
                "productCount",
                productDAO.countByStatus("active"));

        request.setAttribute(
                "totalRevenue",
                billDAO.getTotalRevenue());

        request.setAttribute(
                "totalBills",
                billDAO.getTotalBills());

        request.getRequestDispatcher("/dashboard/dashboard.jsp")
                .forward(request, response);
    }
}