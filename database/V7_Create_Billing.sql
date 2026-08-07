CREATE TABLE bills (
                       id BIGINT PRIMARY KEY AUTO_INCREMENT,

                       invoice_number VARCHAR(20) NOT NULL UNIQUE,

                       customer_id BIGINT NOT NULL,

                       bill_date DATE NOT NULL,

                       subtotal DECIMAL(10,2) NOT NULL,

                       discount DECIMAL(10,2) DEFAULT 0,

                       total_amount DECIMAL(10,2) NOT NULL,

                       status VARCHAR(20) DEFAULT 'PAID',

                       created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

                       CONSTRAINT fk_bill_customer
                           FOREIGN KEY (customer_id)
                               REFERENCES customers(id)
);

CREATE TABLE bill_items (

                            id BIGINT PRIMARY KEY AUTO_INCREMENT,

                            bill_id BIGINT NOT NULL,

                            product_id INT NOT NULL,

                            quantity INT NOT NULL,

                            unit_price DECIMAL(10,2) NOT NULL,

                            line_total DECIMAL(10,2) NOT NULL,

                            CONSTRAINT fk_bill_item_bill
                                FOREIGN KEY (bill_id)
                                    REFERENCES bills(id),

                            CONSTRAINT fk_bill_item_product
                                FOREIGN KEY (product_id)
                                    REFERENCES products(id)

);