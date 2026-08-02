package com.visioncare.service;

import com.visioncare.dao.ProductDAO;
import com.visioncare.model.Product;

import java.util.List;

public class ProductService {

    private final ProductDAO productDAO =
            new ProductDAO();

    public String generateProductCode() {

        String lastCode = productDAO.getNextProductCode();

        if (lastCode == null || lastCode.isBlank()) {
            return "PRD000001";
        }

        int number = Integer.parseInt(
                lastCode.substring(3)
        );

        number++;

        return String.format(
                "PRD%06d",
                number
        );
    }

    /*
    ==========================================================
    Save Product
    ==========================================================
    */

    public void saveProduct(Product product) {
        productDAO.save(product);
    }

    /*
    ==========================================================
    Find Product
    ==========================================================
    */

    public Product getProductById(Long id) {
        return productDAO.findById(id);
    }

    /*
    ==========================================================
    Update Product
    ==========================================================
    */

    public void updateProduct(Product product) {
        productDAO.update(product);
    }

    /*
    ==========================================================
    Deactivate Product
    ==========================================================
    */

    public void deactivateProduct(Long id) {
        productDAO.deactivate(id);
    }

    /*
    ==========================================================
    Product List
    ==========================================================
    */

    public List<Product> getProductsByStatus(String status) {
        return productDAO.findByStatus(status);
    }

    public List<Product> getProductsByStatus(String status,
                                             int offset,
                                             int pageSize) {

        return productDAO.findByStatus(
                status,
                offset,
                pageSize
        );
    }

    /*
    ==========================================================
    Search Products
    ==========================================================
    */

    public List<Product> searchProducts(String keyword,
                                        String status) {

        return productDAO.search(
                keyword,
                status
        );
    }

    public List<Product> searchProducts(String keyword,
                                        String status,
                                        int offset,
                                        int pageSize) {

        return productDAO.search(
                keyword,
                status,
                offset,
                pageSize
        );
    }

    /*
    ==========================================================
    Count Products
    ==========================================================
    */

    public int countProductsByStatus(String status) {
        return productDAO.countByStatus(status);
    }

    public int countSearchResults(String keyword,
                                  String status) {

        return productDAO.countSearchResults(
                keyword,
                status
        );
    }

}