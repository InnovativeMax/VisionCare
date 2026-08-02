package com.visioncare.service;

import com.visioncare.dao.CustomerDAO;
import com.visioncare.model.Customer;

import java.util.List;

/**
 * ==========================================================
 * VisionCare ERP
 * File        : CustomerService.java
 * Description : Customer Service
 * Version     : 1.0
 * ==========================================================
 */
public class CustomerService {

    /*
     * ==========================================================
     * Dependencies
     * ==========================================================
     */

    private final CustomerDAO customerDAO =
            new CustomerDAO();

    /*
     * ==========================================================
     * Get All Customers
     * ==========================================================
     */

    public List<Customer> getAllCustomers() {

        return customerDAO.findAll();

    }

    /*
     * ==========================================================
     * Get Customers By Status
     * ==========================================================
     */

    public List<Customer> getCustomersByStatus(String status) {

        return customerDAO.findByStatus(status);

    }

    /*
     * ==========================================================
     * Get Customers By Status (Paginated)
     * ==========================================================
     */

    public List<Customer> getCustomersByStatus(String status,
                                               int offset,
                                               int pageSize) {

        return customerDAO.findByStatus(
                status,
                offset,
                pageSize
        );

    }

    /*
     * ==========================================================
     * Search Customers
     * ==========================================================
     */

    public List<Customer> searchCustomers(String keyword,
                                          String status) {

        return customerDAO.search(keyword, status);

    }

    /*
     * ==========================================================
     * Search Customers (Paginated)
     * ==========================================================
     */

    public List<Customer> searchCustomers(String keyword,
                                          String status,
                                          int offset,
                                          int pageSize) {

        return customerDAO.search(
                keyword,
                status,
                offset,
                pageSize
        );

    }

    /*
     * ==========================================================
     * Get Customer By ID
     * ==========================================================
     */

    public Customer getCustomerById(Long id) {

        return customerDAO.findById(id);

    }

    /*
     * ==========================================================
     * Generate Customer Code
     * ==========================================================
     */

    private String generateCustomerCode() {

        String lastCustomerCode =
                customerDAO.getNextCustomerCode();

        if (lastCustomerCode == null) {

            return "CUS000001";

        }

        int customerNumber = Integer.parseInt(
                lastCustomerCode.substring(3)
        );

        customerNumber++;

        return String.format(
                "CUS%06d",
                customerNumber
        );

    }

    /*
     * ==========================================================
     * Save Customer
     * ==========================================================
     */

    public void saveCustomer(Customer customer) {

        customer.setCustomerCode(
                generateCustomerCode()
        );

        customerDAO.save(customer);

    }

    /*
     * ==========================================================
     * Update Customer
     * ==========================================================
     */

    public void updateCustomer(Customer customer) {

        customerDAO.update(customer);

    }

    /*
     * ==========================================================
     * Deactivate Customer
     * ==========================================================
     */

    public void deactivateCustomer(Long id) {

        customerDAO.deactivate(id);

    }

    /*
     * ==========================================================
     * Count Customers By Status
     * ==========================================================
     */

    public int countCustomersByStatus(String status) {

        return customerDAO.countByStatus(status);

    }

    /*
     * ==========================================================
     * Count Search Results
     * ==========================================================
     */

    public int countSearchResults(String keyword,
                                  String status) {

        return customerDAO.countSearchResults(
                keyword,
                status
        );

    }
}