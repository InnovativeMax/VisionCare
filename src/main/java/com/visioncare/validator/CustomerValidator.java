package com.visioncare.validator;

import com.visioncare.model.Customer;

import java.util.ArrayList;
import java.util.List;

/**
 * ==========================================================
 * VisionCare ERP
 * File        : CustomerValidator.java
 * Description : Customer Validation
 * Version     : 1.0
 * ==========================================================
 */
public class CustomerValidator {

    /*
     * ==========================================================
     * Validate Customer
     * ==========================================================
     */

    public List<String> validate(Customer customer) {

        List<String> errors =
                new ArrayList<>();

        /*
         * ======================================================
         * Full Name
         * ======================================================
         */

        if (customer.getFullName() == null ||
                customer.getFullName().isBlank()) {

            errors.add(
                    "Full Name is required."
            );

        }

        /*
         * ======================================================
         * Mobile
         * ======================================================
         */

        if (customer.getMobileNumber() == null ||
                customer.getMobileNumber().isBlank()) {

            errors.add(
                    "Mobile Number is required."
            );

        }

        return errors;

    }

}