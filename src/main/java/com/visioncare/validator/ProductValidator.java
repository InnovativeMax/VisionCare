package com.visioncare.validator;

import com.visioncare.model.Product;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * ==========================================================
 * VisionCare ERP
 * File        : ProductValidator.java
 * Description : Product Validation
 * Version     : 1.0
 * ==========================================================
 */
public class ProductValidator {

    /*
     * ==========================================================
     * Validate Product
     * ==========================================================
     */

    public List<String> validate(Product product) {

        List<String> errors = new ArrayList<>();

        /*
         * ======================================================
         * Product Name
         * ======================================================
         */

        if (product.getProductName() == null ||
                product.getProductName().isBlank()) {

            errors.add("Product Name is required.");
        }

        /*
         * ======================================================
         * Category
         * ======================================================
         */

        if (product.getCategory() == null ||
                product.getCategory().isBlank()) {

            errors.add("Category is required.");
        }

        /*
         * ======================================================
         * Brand
         * ======================================================
         */

        if (product.getBrand() == null ||
                product.getBrand().isBlank()) {

            errors.add("Brand is required.");
        }

        /*
         * ======================================================
         * Cost Price
         * ======================================================
         */

        if (product.getCostPrice() == null) {

            errors.add("Cost Price is required.");

        } else if (product.getCostPrice()
                .compareTo(BigDecimal.ZERO) < 0) {

            errors.add("Cost Price cannot be negative.");
        }

        /*
         * ======================================================
         * Selling Price
         * ======================================================
         */

        if (product.getSellingPrice() == null) {

            errors.add("Selling Price is required.");

        } else if (product.getSellingPrice()
                .compareTo(BigDecimal.ZERO) < 0) {

            errors.add("Selling Price cannot be negative.");

        } else if (product.getCostPrice() != null &&
                product.getSellingPrice()
                        .compareTo(product.getCostPrice()) < 0) {

            errors.add("Selling Price cannot be less than Cost Price.");
        }

        /*
         * ======================================================
         * Stock Quantity
         * ======================================================
         */

        if (product.getStockQuantity() < 0) {

            errors.add("Stock Quantity cannot be negative.");
        }

        /*
         * ======================================================
         * Reorder Level
         * ======================================================
         */

        if (product.getReorderLevel() < 0) {

            errors.add("Reorder Level cannot be negative.");
        }

        return errors;
    }

}