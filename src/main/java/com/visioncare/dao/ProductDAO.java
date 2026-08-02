package com.visioncare.dao;

import com.visioncare.config.DBConnection;
import com.visioncare.model.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {
    /*
     * ==========================================================
     * Get Next Product Code
     * ==========================================================
     */
    public String getNextProductCode() {
        String sql = """
                SELECT product_code
                FROM products
                ORDER BY id DESC
                LIMIT 1
                """;
        try (
                Connection connection =
                        DBConnection.getConnection();
                PreparedStatement statement =
                        connection.prepareStatement(sql);
                ResultSet resultSet =
                        statement.executeQuery()
        ) {
            if (resultSet.next()) {
                return resultSet.getString("product_code");
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return null;
    }

    /*
     * ==========================================================
     * ResultSet Mapper
     * ==========================================================
     */

    private Product mapProduct(ResultSet resultSet)
            throws SQLException {

        Product product = new Product();

        product.setId(
                resultSet.getInt("id")
        );

        product.setProductCode(
                resultSet.getString("product_code")
        );

        product.setProductName(
                resultSet.getString("product_name")
        );

        product.setCategory(
                resultSet.getString("category")
        );

        product.setBrand(
                resultSet.getString("brand")
        );

        product.setCostPrice(
                resultSet.getBigDecimal("cost_price")
        );

        product.setSellingPrice(
                resultSet.getBigDecimal("selling_price")
        );

        product.setStockQuantity(
                resultSet.getInt("stock_quantity")
        );

        product.setReorderLevel(
                resultSet.getInt("reorder_level")
        );

        product.setDescription(
                resultSet.getString("description")
        );

        product.setActive(
                resultSet.getBoolean("is_active")
        );

        if (resultSet.getTimestamp("created_at") != null) {

            product.setCreatedAt(
                    resultSet.getTimestamp("created_at")
                            .toLocalDateTime()
            );

        }

        if (resultSet.getTimestamp("updated_at") != null) {

            product.setUpdatedAt(
                    resultSet.getTimestamp("updated_at")
                            .toLocalDateTime()
            );

        }

        return product;
    }

    /*
     * ==========================================================
     * Save Product
     * ==========================================================
     */

    public void save(Product product) {

        String sql = """
                INSERT INTO products (
                
                    product_code,
                    product_name,
                    category,
                    brand,
                    cost_price,
                    selling_price,
                    stock_quantity,
                    reorder_level,
                    description,
                    is_active
                
                )
                
                VALUES (
                
                    ?, ?, ?, ?, ?, ?, ?, ?, ?, ?
                
                )
                """;

        try (

                Connection connection =
                        DBConnection.getConnection();

                PreparedStatement statement =
                        connection.prepareStatement(sql)

        ) {

            statement.setString(
                    1,
                    product.getProductCode()
            );

            statement.setString(
                    2,
                    product.getProductName()
            );

            statement.setString(
                    3,
                    product.getCategory()
            );

            statement.setString(
                    4,
                    product.getBrand()
            );

            statement.setBigDecimal(
                    5,
                    product.getCostPrice()
            );

            statement.setBigDecimal(
                    6,
                    product.getSellingPrice()
            );

            statement.setInt(
                    7,
                    product.getStockQuantity()
            );

            statement.setInt(
                    8,
                    product.getReorderLevel()
            );

            statement.setString(
                    9,
                    product.getDescription()
            );

            statement.setBoolean(
                    10,
                    true
            );

            statement.executeUpdate();

        } catch (SQLException exception) {

            exception.printStackTrace();

        }
    }

    /*
     * ==========================================================
     * Find Product By ID
     * ==========================================================
     */

    public Product findById(Long id) {

        String sql = """
                SELECT
                    id,
                    product_code,
                    product_name,
                    category,
                    brand,
                    cost_price,
                    selling_price,
                    stock_quantity,
                    reorder_level,
                    description,
                    is_active,
                    created_at,
                    updated_at
                FROM products
                WHERE id = ?
                """;

        try (

                Connection connection =
                        DBConnection.getConnection();

                PreparedStatement statement =
                        connection.prepareStatement(sql)

        ) {

            statement.setLong(
                    1,
                    id
            );

            try (

                    ResultSet resultSet =
                            statement.executeQuery()

            ) {

                if (resultSet.next()) {

                    return mapProduct(resultSet);

                }

            }

        } catch (SQLException exception) {

            exception.printStackTrace();

        }

        return null;

    }

    /*
     * ==========================================================
     * Update Product
     * ==========================================================
     */

    public void update(Product product) {

        String sql = """
                UPDATE products
                SET
                    product_name = ?,
                    category = ?,
                    brand = ?,
                    cost_price = ?,
                    selling_price = ?,
                    stock_quantity = ?,
                    reorder_level = ?,
                    description = ?
                WHERE id = ?
                """;

        try (

                Connection connection =
                        DBConnection.getConnection();

                PreparedStatement statement =
                        connection.prepareStatement(sql)

        ) {

            statement.setString(
                    1,
                    product.getProductName()
            );

            statement.setString(
                    2,
                    product.getCategory()
            );

            statement.setString(
                    3,
                    product.getBrand()
            );

            statement.setBigDecimal(
                    4,
                    product.getCostPrice()
            );

            statement.setBigDecimal(
                    5,
                    product.getSellingPrice()
            );

            statement.setInt(
                    6,
                    product.getStockQuantity()
            );

            statement.setInt(
                    7,
                    product.getReorderLevel()
            );

            statement.setString(
                    8,
                    product.getDescription()
            );

            statement.setLong(
                    9,
                    product.getId()
            );

            statement.executeUpdate();

        } catch (SQLException exception) {

            exception.printStackTrace();

        }

    }

    /*
     * ==========================================================
     * Deactivate Product
     * ==========================================================
     */

    public void deactivate(Long id) {

        String sql = """
                UPDATE products
                SET is_active = FALSE
                WHERE id = ?
                """;

        try (

                Connection connection =
                        DBConnection.getConnection();

                PreparedStatement statement =
                        connection.prepareStatement(sql)

        ) {

            statement.setLong(
                    1,
                    id
            );

            statement.executeUpdate();

        } catch (SQLException exception) {

            exception.printStackTrace();

        }

    }

    /*
     * ==========================================================
     * Find Products By Status
     * ==========================================================
     */

    public List<Product> findByStatus(String status) {

        String sql = """
                SELECT *
                FROM products
                """;

        switch (status) {

            case "active" -> sql += " WHERE is_active = TRUE";

            case "inactive" -> sql += " WHERE is_active = FALSE";

            case "all" -> {
                // No WHERE clause
            }

            default -> sql += " WHERE is_active = TRUE";

        }

        sql += " ORDER BY id DESC";

        List<Product> products = new ArrayList<>();

        try (

                Connection connection =
                        DBConnection.getConnection();

                PreparedStatement statement =
                        connection.prepareStatement(sql);

                ResultSet resultSet =
                        statement.executeQuery()

        ) {

            while (resultSet.next()) {

                products.add(
                        mapProduct(resultSet)
                );

            }

        } catch (SQLException exception) {

            exception.printStackTrace();

        }

        return products;

    }

    /*
     * ==========================================================
     * Find Products By Status (Paginated)
     * ==========================================================
     */

    public List<Product> findByStatus(String status,
                                      int offset,
                                      int pageSize) {

        String sql = """
                SELECT *
                FROM products
                """;

        switch (status) {

            case "active" -> sql += " WHERE is_active = TRUE ";

            case "inactive" -> sql += " WHERE is_active = FALSE ";

            case "all" -> {
                // No WHERE clause
            }

            default -> sql += " WHERE is_active = TRUE ";

        }

        sql += """
                ORDER BY id DESC
                LIMIT ? OFFSET ?
                """;

        List<Product> products = new ArrayList<>();

        try (

                Connection connection =
                        DBConnection.getConnection();

                PreparedStatement statement =
                        connection.prepareStatement(sql)

        ) {

            statement.setInt(
                    1,
                    pageSize
            );

            statement.setInt(
                    2,
                    offset
            );

            try (

                    ResultSet resultSet =
                            statement.executeQuery()

            ) {

                while (resultSet.next()) {

                    products.add(
                            mapProduct(resultSet)
                    );

                }

            }

        } catch (SQLException exception) {

            exception.printStackTrace();

        }

        return products;

    }

    /*
     * ==========================================================
     * Search Products
     * ==========================================================
     */

    public List<Product> search(String keyword, String status) {

        String sql = """
                SELECT *
                FROM products
                WHERE (
                        product_code LIKE ?
                        OR product_name LIKE ?
                        OR category LIKE ?
                        OR brand LIKE ?
                )
                """;

        switch (status) {

            case "active" -> sql += " AND is_active = TRUE";

            case "inactive" -> sql += " AND is_active = FALSE";

            case "all" -> {
                // No additional filter
            }

            default -> sql += " AND is_active = TRUE";

        }

        sql += " ORDER BY product_name";

        List<Product> products = new ArrayList<>();

        try (

                Connection connection =
                        DBConnection.getConnection();

                PreparedStatement statement =
                        connection.prepareStatement(sql)

        ) {

            String search = "%" + keyword + "%";

            statement.setString(1, search);
            statement.setString(2, search);
            statement.setString(3, search);
            statement.setString(4, search);

            try (

                    ResultSet resultSet =
                            statement.executeQuery()

            ) {

                while (resultSet.next()) {

                    products.add(
                            mapProduct(resultSet)
                    );

                }

            }

        } catch (SQLException exception) {

            exception.printStackTrace();

        }

        return products;

    }

    /*
     * ==========================================================
     * Search Products (Paginated)
     * ==========================================================
     */

    public List<Product> search(String keyword,
                                String status,
                                int offset,
                                int pageSize) {

        String sql = """
                SELECT *
                FROM products
                WHERE (
                        product_code LIKE ?
                        OR product_name LIKE ?
                        OR category LIKE ?
                        OR brand LIKE ?
                )
                """;

        switch (status) {

            case "active" -> sql += " AND is_active = TRUE";

            case "inactive" -> sql += " AND is_active = FALSE";

            case "all" -> {
                // No additional condition
            }

            default -> sql += " AND is_active = TRUE";

        }

        sql += """
                ORDER BY id DESC
                LIMIT ? OFFSET ?
                """;

        List<Product> products = new ArrayList<>();

        try (

                Connection connection =
                        DBConnection.getConnection();

                PreparedStatement statement =
                        connection.prepareStatement(sql)

        ) {

            String search = "%" + keyword + "%";

            statement.setString(1, search);
            statement.setString(2, search);
            statement.setString(3, search);
            statement.setString(4, search);

            statement.setInt(5, pageSize);
            statement.setInt(6, offset);

            try (

                    ResultSet resultSet =
                            statement.executeQuery()

            ) {

                while (resultSet.next()) {

                    products.add(
                            mapProduct(resultSet)
                    );

                }

            }

        } catch (SQLException exception) {

            exception.printStackTrace();

        }

        return products;

    }

    /*
     * ==========================================================
     * Count Products By Status
     * ==========================================================
     */

    public int countByStatus(String status) {

        String sql = """
                SELECT COUNT(*)
                FROM products
                """;

        switch (status) {

            case "active" -> sql += " WHERE is_active = TRUE ";

            case "inactive" -> sql += " WHERE is_active = FALSE ";

            case "all" -> {
                // No WHERE clause
            }

            default -> sql += " WHERE is_active = TRUE ";

        }

        try (

                Connection connection =
                        DBConnection.getConnection();

                PreparedStatement statement =
                        connection.prepareStatement(sql);

                ResultSet resultSet =
                        statement.executeQuery()

        ) {

            if (resultSet.next()) {

                return resultSet.getInt(1);

            }

        } catch (SQLException exception) {

            exception.printStackTrace();

        }

        return 0;

    }

    /*
     * ==========================================================
     * Count Search Results
     * ==========================================================
     */

    public int countSearchResults(String keyword,
                                  String status) {

        String sql = """
                SELECT COUNT(*)
                FROM products
                WHERE (
                        product_code LIKE ?
                        OR product_name LIKE ?
                        OR category LIKE ?
                        OR brand LIKE ?
                )
                """;

        switch (status) {

            case "active" -> sql += " AND is_active = TRUE ";

            case "inactive" -> sql += " AND is_active = FALSE ";

            case "all" -> {
                // No additional condition
            }

            default -> sql += " AND is_active = TRUE ";

        }

        try (

                Connection connection =
                        DBConnection.getConnection();

                PreparedStatement statement =
                        connection.prepareStatement(sql)

        ) {

            String search = "%" + keyword + "%";

            statement.setString(1, search);
            statement.setString(2, search);
            statement.setString(3, search);
            statement.setString(4, search);

            try (

                    ResultSet resultSet =
                            statement.executeQuery()

            ) {

                if (resultSet.next()) {

                    return resultSet.getInt(1);

                }

            }

        } catch (SQLException exception) {

            exception.printStackTrace();

        }

        return 0;

    }
}
