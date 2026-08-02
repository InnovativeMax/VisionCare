package com.visioncare.dao;

import com.visioncare.config.DBConnection;
import com.visioncare.model.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

/**
 * ==========================================================
 * VisionCare ERP
 * File        : CustomerDAO.java
 * Description : Customer Data Access Object
 * Version     : 1.0
 * ==========================================================
 */
public class CustomerDAO {

    /*
     * ==========================================================
     * Find All Customers
     * ==========================================================
     */

    public List<Customer> findAll() {

        List<Customer> customers = new ArrayList<>();

        String sql = """
                SELECT
                    id,
                    customer_code,
                    full_name,
                    mobile_number,
                    email,
                    date_of_birth,
                    gender,
                    address_line1,
                    address_line2,
                    city,
                    state,
                    pincode,
                    notes,
                    is_active
                FROM customers
                WHERE is_active = TRUE
                ORDER BY full_name
                """;

        try (
                Connection connection =
                        DBConnection.getConnection();

                PreparedStatement statement =
                        connection.prepareStatement(sql);

                ResultSet resultSet =
                        statement.executeQuery()
        ) {

            while (resultSet.next()) {

                customers.add(
                        mapCustomer(resultSet)
                );

            }

        } catch (SQLException exception) {

            exception.printStackTrace();

        }

        return customers;

    }

    /*
     * ==========================================================
     * Find Customer By ID
     * ==========================================================
     */

    public Customer findById(Long id) {

        String sql = """
                SELECT
                    id,
                    customer_code,
                    full_name,
                    mobile_number,
                    email,
                    date_of_birth,
                    gender,
                    address_line1,
                    address_line2,
                    city,
                    state,
                    pincode,
                    notes,
                    is_active
                FROM customers
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

                    return mapCustomer(resultSet);

                }

            }

        } catch (SQLException exception) {

            exception.printStackTrace();

        }

        return null;

    }

    /*
     * ==========================================================
     * Get Next Customer Code
     * ==========================================================
     */

    public String getNextCustomerCode() {

        String sql = """
                SELECT customer_code
                FROM customers
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

                return resultSet.getString(
                        "customer_code"
                );

            }

        } catch (SQLException exception) {

            exception.printStackTrace();

        }

        return null;

    }

    /*
     * ==========================================================
     * Save Customer
     * ==========================================================
     */

    public void save(Customer customer) {

        String sql = """
                INSERT INTO customers (
                    customer_code,
                    full_name,
                    mobile_number,
                    email,
                    date_of_birth,
                    gender,
                    address_line1,
                    address_line2,
                    city,
                    state,
                    pincode,
                    notes,
                    is_active
                )
                VALUES (
                    ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?
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
                    customer.getCustomerCode()
            );

            statement.setString(
                    2,
                    customer.getFullName()
            );

            statement.setString(
                    3,
                    customer.getMobileNumber()
            );

            statement.setString(
                    4,
                    customer.getEmail()
            );

            if (customer.getDateOfBirth() != null) {

                statement.setDate(
                        5,
                        java.sql.Date.valueOf(
                                customer.getDateOfBirth()
                        )
                );

            } else {

                statement.setNull(
                        5,
                        java.sql.Types.DATE
                );

            }

            if (customer.getGender() != null &&
                    !customer.getGender().isBlank()) {

                statement.setString(
                        6,
                        customer.getGender()
                );

            } else {

                statement.setNull(
                        6,
                        java.sql.Types.VARCHAR
                );

            }

            statement.setString(
                    7,
                    customer.getAddressLine1()
            );

            statement.setString(
                    8,
                    customer.getAddressLine2()
            );

            statement.setString(
                    9,
                    customer.getCity()
            );

            statement.setString(
                    10,
                    customer.getState()
            );

            statement.setString(
                    11,
                    customer.getPincode()
            );

            statement.setString(
                    12,
                    customer.getNotes()
            );

            statement.setBoolean(
                    13,
                    true
            );

            statement.executeUpdate();
        } catch (SQLException exception) {

            exception.printStackTrace();

        }

    }

    /*
     * ==========================================================
     * Update Customer
     * ==========================================================
     */

    public void update(Customer customer) {

        String sql = """
                UPDATE customers
                SET
                    full_name = ?,
                    mobile_number = ?,
                    email = ?,
                    date_of_birth = ?,
                    gender = ?,
                    address_line1 = ?,
                    address_line2 = ?,
                    city = ?,
                    state = ?,
                    pincode = ?,
                    notes = ?
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
                    customer.getFullName()
            );

            statement.setString(
                    2,
                    customer.getMobileNumber()
            );

            statement.setString(
                    3,
                    customer.getEmail()
            );

            if (customer.getDateOfBirth() != null) {

                statement.setDate(
                        4,
                        java.sql.Date.valueOf(
                                customer.getDateOfBirth()
                        )
                );

            } else {

                statement.setNull(
                        4,
                        java.sql.Types.DATE
                );

            }

            if (customer.getGender() != null &&
                    !customer.getGender().isBlank()) {

                statement.setString(
                        5,
                        customer.getGender()
                );

            } else {

                statement.setNull(
                        5,
                        java.sql.Types.VARCHAR
                );

            }

            statement.setString(
                    6,
                    customer.getAddressLine1()
            );

            statement.setString(
                    7,
                    customer.getAddressLine2()
            );

            statement.setString(
                    8,
                    customer.getCity()
            );

            statement.setString(
                    9,
                    customer.getState()
            );

            statement.setString(
                    10,
                    customer.getPincode()
            );

            statement.setString(
                    11,
                    customer.getNotes()
            );

            statement.setLong(
                    12,
                    customer.getId()
            );

            statement.executeUpdate();

        } catch (SQLException exception) {

            exception.printStackTrace();

        }

    }

    /*
     * ==========================================================
     * Deactivate Customer
     * ==========================================================
     */

    public void deactivate(Long id) {

        String sql = """
                UPDATE customers
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
     * ResultSet Mapper
     * ==========================================================
     */

    private Customer mapCustomer(ResultSet resultSet)
            throws SQLException {

        Customer customer = new Customer();

        customer.setId(
                resultSet.getLong("id")
        );

        customer.setCustomerCode(
                resultSet.getString("customer_code")
        );

        customer.setFullName(
                resultSet.getString("full_name")
        );

        customer.setMobileNumber(
                resultSet.getString("mobile_number")
        );

        customer.setEmail(
                resultSet.getString("email")
        );

        if (resultSet.getDate("date_of_birth") != null) {

            customer.setDateOfBirth(
                    resultSet.getDate("date_of_birth")
                            .toLocalDate()
            );

        }

        customer.setGender(
                resultSet.getString("gender")
        );

        customer.setCity(
                resultSet.getString("city")
        );

        customer.setAddressLine1(
                resultSet.getString("address_line1")
        );

        customer.setAddressLine2(
                resultSet.getString("address_line2")
        );

        customer.setState(
                resultSet.getString("state")
        );

        customer.setPincode(
                resultSet.getString("pincode")
        );

        customer.setNotes(
                resultSet.getString("notes")
        );

        customer.setActive(
                resultSet.getBoolean("is_active")
        );

        return customer;

    }

    /*
     * ==========================================================
     * Find Customers By Status
     * ==========================================================
     */

    public List<Customer> findByStatus(String status) {

        String sql = """
                SELECT *
                FROM customers
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

        List<Customer> customers =
                new ArrayList<>();

        try (

                Connection connection =
                        DBConnection.getConnection();

                PreparedStatement statement =
                        connection.prepareStatement(sql);

                ResultSet resultSet =
                        statement.executeQuery()

        ) {

            while (resultSet.next()) {

                customers.add(
                        mapCustomer(resultSet)
                );

            }

        } catch (SQLException exception) {

            exception.printStackTrace();

        }

        return customers;

    }

    /*
     * ==========================================================
     * Find Customers By Status (Paginated)
     * ==========================================================
     */

    public List<Customer> findByStatus(String status,
                                       int offset,
                                       int pageSize) {

        String sql = """
                SELECT *
                FROM customers
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

        List<Customer> customers = new ArrayList<>();

        try (

                Connection connection =
                        DBConnection.getConnection();

                PreparedStatement statement =
                        connection.prepareStatement(sql)

        ) {

            statement.setInt(1, pageSize);
            statement.setInt(2, offset);

            try (

                    ResultSet resultSet =
                            statement.executeQuery()

            ) {

                while (resultSet.next()) {

                    customers.add(
                            mapCustomer(resultSet)
                    );

                }

            }

        } catch (SQLException exception) {

            exception.printStackTrace();

        }

        return customers;

    }

    /*
     * ==========================================================
     * Search Customers
     * ==========================================================
     */

    public List<Customer> search(String keyword, String status) {

        String sql = """
                SELECT *
                FROM customers
                WHERE
                (
                    customer_code LIKE ?
                    OR full_name LIKE ?
                    OR mobile_number LIKE ?
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

        sql += " ORDER BY full_name";

        List<Customer> customers =
                new ArrayList<>();

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

            try (

                    ResultSet resultSet =
                            statement.executeQuery()

            ) {

                while (resultSet.next()) {

                    customers.add(
                            mapCustomer(resultSet)
                    );

                }

            }

        } catch (SQLException exception) {

            exception.printStackTrace();

        }

        return customers;

    }

    /*
     * ==========================================================
     * Search Customers (Paginated)
     * ==========================================================
     */

    public List<Customer> search(String keyword,
                                 String status,
                                 int offset,
                                 int pageSize) {

        String sql = """
                SELECT *
                FROM customers
                WHERE (customer_code LIKE ?
                OR full_name LIKE ?
                OR mobile_number LIKE ?)
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

        List<Customer> customers = new ArrayList<>();

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

            statement.setInt(4, pageSize);
            statement.setInt(5, offset);

            try (

                    ResultSet resultSet =
                            statement.executeQuery()

            ) {

                while (resultSet.next()) {

                    customers.add(
                            mapCustomer(resultSet)
                    );

                }

            }

        } catch (SQLException exception) {

            exception.printStackTrace();

        }

        return customers;

    }

    /*
     * ==========================================================
     * Count Customers By Status
     * ==========================================================
     */

    public int countByStatus(String status) {

        String sql = """
                SELECT COUNT(*)
                FROM customers
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
                FROM customers
                WHERE (
                        customer_code LIKE ?
                        OR full_name LIKE ?
                        OR mobile_number LIKE ?
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