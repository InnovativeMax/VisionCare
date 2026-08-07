package com.visioncare.service;

import com.visioncare.dao.BillDAO;
import com.visioncare.dao.CustomerDAO;
import com.visioncare.dao.ProductDAO;
import com.visioncare.model.Bill;
import com.visioncare.model.Customer;
import com.visioncare.model.Product;

import java.util.List;

public class BillingService {

    private final BillDAO billDAO = new BillDAO();
    private final CustomerDAO customerDAO = new CustomerDAO();
    private final ProductDAO productDAO = new ProductDAO();

    /**
     * Generate Next Invoice Number
     */
    public String generateInvoiceNumber() {
        return billDAO.generateInvoiceNumber();
    }

    /**
     * Save Complete Bill
     */
    public void saveBill(Bill bill) {
        billDAO.saveBill(bill);
    }

    /**
     * Active Customers
     */
    public List<Customer> getActiveCustomers() {
        return customerDAO.findByStatus("active");
    }

    /**
     * Active Products
     */
    public List<Product> getActiveProducts() {
        return productDAO.findByStatus("active");
    }

    /**
     * Bill Details
     */
    public Bill getBill(Long id) {
        return billDAO.findById(id);
    }

    /**
     * Bill List
     */
    public List<Bill> getAllBills() {
        return billDAO.findAll();
    }
}