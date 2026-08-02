/*
==========================================================
VisionCare ERP
Module      : Customer Management
File        : V5_Create_Customers.sql
Description : Creates Customer Master Table
Version     : 1.0
==========================================================
*/

CREATE TABLE customers (

                           id BIGINT PRIMARY KEY AUTO_INCREMENT,

                           customer_code VARCHAR(20) NOT NULL UNIQUE,

                           full_name VARCHAR(100) NOT NULL,

                           mobile_number VARCHAR(15) NOT NULL,

                           email VARCHAR(100),

                           date_of_birth DATE,

                           gender ENUM (
        'Male',
        'Female',
        'Other'
    ),

                           address_line1 VARCHAR(255),

                           address_line2 VARCHAR(255),

                           city VARCHAR(100),

                           state VARCHAR(100),

                           pincode VARCHAR(10),

                           notes TEXT,

                           is_active BOOLEAN NOT NULL DEFAULT TRUE,

                           created_at TIMESTAMP NOT NULL
                                                      DEFAULT CURRENT_TIMESTAMP,

                           created_by BIGINT,

                           updated_at TIMESTAMP NOT NULL
                                                      DEFAULT CURRENT_TIMESTAMP
                               ON UPDATE CURRENT_TIMESTAMP,

                           updated_by BIGINT,

                           CONSTRAINT fk_customer_created_by
                               FOREIGN KEY (created_by)
                                   REFERENCES users(id),

                           CONSTRAINT fk_customer_updated_by
                               FOREIGN KEY (updated_by)
                                   REFERENCES users(id)

);

/*
==========================================================
Indexes
==========================================================
*/

CREATE INDEX idx_customer_code
    ON customers(customer_code);

CREATE INDEX idx_customer_name
    ON customers(full_name);

CREATE INDEX idx_customer_mobile
    ON customers(mobile_number);