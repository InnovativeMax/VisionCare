CREATE TABLE products (

    id INT AUTO_INCREMENT PRIMARY KEY,

    product_code VARCHAR(20) NOT NULL UNIQUE,

    product_name VARCHAR(150) NOT NULL,

    category VARCHAR(100) NOT NULL,

    brand VARCHAR(100),

    cost_price DECIMAL(10,2) NOT NULL,

    selling_price DECIMAL(10,2) NOT NULL,

    stock_quantity INT NOT NULL DEFAULT 0,

    reorder_level INT NOT NULL DEFAULT 0,

    description TEXT,

    is_active BOOLEAN NOT NULL DEFAULT TRUE,

    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
        ON UPDATE CURRENT_TIMESTAMP

);