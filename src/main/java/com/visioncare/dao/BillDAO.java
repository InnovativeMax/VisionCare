package com.visioncare.dao;

import com.visioncare.config.DBConnection;
import com.visioncare.model.Bill;
import com.visioncare.model.BillItem;
import com.visioncare.model.Customer;
import com.visioncare.model.Product;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class BillDAO {

    public String generateInvoiceNumber() {

        String sql = """
                SELECT invoice_number
                FROM bills
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

                String last =
                        resultSet.getString("invoice_number");

                int number =
                        Integer.parseInt(last.substring(3));

                return String.format(
                        "INV%06d",
                        number + 1
                );

            }

        } catch (SQLException exception) {

            exception.printStackTrace();

        }

        return "INV000001";

    }

    /**
     * ==========================================================
     * Save Bill
     * ==========================================================
     */
    public void saveBill(Bill bill) {

        String billSql = """
                INSERT INTO bills (
                    invoice_number,
                    customer_id,
                    bill_date,
                    subtotal,
                    discount,
                    total_amount,
                    status
                )
                VALUES (?, ?, ?, ?, ?, ?, ?)
                """;

        String itemSql = """
                INSERT INTO bill_items (
                    bill_id,
                    product_id,
                    quantity,
                    unit_price,
                    line_total
                )
                VALUES (?, ?, ?, ?, ?)
                """;

        Connection connection = null;

        try {

            connection = DBConnection.getConnection();

            connection.setAutoCommit(false);

            Long billId;

            try (

                    PreparedStatement statement =
                            connection.prepareStatement(
                                    billSql,
                                    Statement.RETURN_GENERATED_KEYS
                            )

            ) {

                statement.setString(
                        1,
                        bill.getInvoiceNumber()
                );

                statement.setLong(
                        2,
                        bill.getCustomerId()
                );

                statement.setDate(
                        3,
                        Date.valueOf(
                                bill.getBillDate()
                        )
                );

                statement.setBigDecimal(
                        4,
                        bill.getSubtotal()
                );

                statement.setBigDecimal(
                        5,
                        bill.getDiscount()
                );

                statement.setBigDecimal(
                        6,
                        bill.getTotalAmount()
                );

                statement.setString(
                        7,
                        "PAID"
                );

                statement.executeUpdate();

                ResultSet keys =
                        statement.getGeneratedKeys();

                keys.next();

                billId =
                        keys.getLong(1);

            }

            for (BillItem item : bill.getItems()) {

                if (!hasEnoughStock(
                        connection,
                        item.getProductId(),
                        item.getQuantity())) {

                    throw new SQLException(
                            "Insufficient stock for product ID : "
                                    + item.getProductId()
                    );

                }

                try (

                        PreparedStatement statement =
                                connection.prepareStatement(itemSql)

                ) {

                    statement.setLong(
                            1,
                            billId
                    );

                    statement.setInt(
                            2,
                            item.getProductId()
                    );

                    statement.setInt(
                            3,
                            item.getQuantity()
                    );

                    statement.setBigDecimal(
                            4,
                            item.getUnitPrice()
                    );

                    statement.setBigDecimal(
                            5,
                            item.getLineTotal()
                    );

                    statement.executeUpdate();

                }

                reduceStock(
                        connection,
                        item.getProductId(),
                        item.getQuantity()
                );

            }

            connection.commit();

        } catch (Exception exception) {

            try {

                if (connection != null) {

                    connection.rollback();

                }

            } catch (SQLException rollbackException) {

                rollbackException.printStackTrace();

            }

            exception.printStackTrace();

        } finally {

            try {

                if (connection != null) {

                    connection.setAutoCommit(true);

                    connection.close();

                }

            } catch (SQLException exception) {

                exception.printStackTrace();

            }

        }

    }

    /**
     * ==========================================================
     * Check Product Stock
     * ==========================================================
     */
    private boolean hasEnoughStock(
            Connection connection,
            int productId,
            int quantity)
            throws SQLException {

        String sql = """
                SELECT stock_quantity
                FROM products
                WHERE id = ?
                """;

        try (

                PreparedStatement statement =
                        connection.prepareStatement(sql)

        ) {

            statement.setInt(
                    1,
                    productId
            );

            try (

                    ResultSet resultSet =
                            statement.executeQuery()

            ) {

                if (resultSet.next()) {

                    return resultSet.getInt("stock_quantity")
                            >= quantity;

                }

            }

        }

        return false;

    }

    /**
     * ==========================================================
     * Reduce Product Stock
     * ==========================================================
     */
    private void reduceStock(
            Connection connection,
            int productId,
            int quantity)
            throws SQLException {

        String sql = """
                UPDATE products
                SET stock_quantity =
                    stock_quantity - ?
                WHERE id = ?
                """;

        try (

                PreparedStatement statement =
                        connection.prepareStatement(sql)

        ) {

            statement.setInt(
                    1,
                    quantity
            );

            statement.setInt(
                    2,
                    productId
            );

            statement.executeUpdate();

        }

    }

    /**
     * ==========================================================
     * Find All Bills
     * ==========================================================
     */
    public List<Bill> findAll() {

        List<Bill> bills = new ArrayList<>();

        String sql = """
                SELECT
                    b.id,
                    b.invoice_number,
                    b.customer_id,
                    c.customer_code,
                    c.full_name,
                    b.bill_date,
                    b.subtotal,
                    b.discount,
                    b.total_amount,
                    b.status,
                    b.created_at
                FROM bills b
                INNER JOIN customers c
                    ON b.customer_id = c.id
                ORDER BY b.id DESC
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

                bills.add(
                        mapBill(resultSet)
                );

            }

        } catch (SQLException exception) {

            exception.printStackTrace();

        }

        return bills;

    }

    /**
     * ==========================================================
     * Find Bill By ID
     * ==========================================================
     */
    public Bill findById(Long id) {

        String sql = """
                SELECT
                    b.id,
                    b.invoice_number,
                    b.customer_id,
                    c.customer_code,
                    c.full_name,
                    b.bill_date,
                    b.subtotal,
                    b.discount,
                    b.total_amount,
                    b.status,
                    b.created_at
                FROM bills b
                INNER JOIN customers c
                    ON b.customer_id = c.id
                WHERE b.id = ?
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

                    Bill bill =
                            mapBill(resultSet);

                    bill.setItems(
                            findBillItems(
                                    bill.getId()
                            )
                    );

                    return bill;

                }

            }

        } catch (SQLException exception) {

            exception.printStackTrace();

        }

        return null;

    }

    /**
     * ==========================================================
     * Find Bill Items
     * ==========================================================
     */
    private List<BillItem> findBillItems(Long billId) {

        List<BillItem> items =
                new ArrayList<>();

        String sql = """
                SELECT
                    bi.id,
                    bi.bill_id,
                    bi.product_id,
                    bi.quantity,
                    bi.unit_price,
                    bi.line_total,
                
                    p.product_code,
                    p.product_name
                
                FROM bill_items bi
                
                INNER JOIN products p
                    ON bi.product_id = p.id
                
                WHERE bi.bill_id = ?
                ORDER BY bi.id
                """;

        try (

                Connection connection =
                        DBConnection.getConnection();

                PreparedStatement statement =
                        connection.prepareStatement(sql)

        ) {

            statement.setLong(
                    1,
                    billId
            );

            try (

                    ResultSet resultSet =
                            statement.executeQuery()

            ) {

                while (resultSet.next()) {

                    items.add(
                            mapBillItem(resultSet)
                    );

                }

            }

        } catch (SQLException exception) {

            exception.printStackTrace();

        }

        return items;

    }

    private Bill mapBill(ResultSet rs) throws SQLException {

        Bill bill = new Bill();

        bill.setId(
                rs.getLong("id")
        );

        bill.setInvoiceNumber(
                rs.getString("invoice_number")
        );

        bill.setCustomerId(
                rs.getLong("customer_id")
        );

        bill.setBillDate(
                rs.getDate("bill_date").toLocalDate()
        );

        bill.setSubtotal(
                rs.getBigDecimal("subtotal")
        );

        bill.setDiscount(
                rs.getBigDecimal("discount")
        );

        bill.setTotalAmount(
                rs.getBigDecimal("total_amount")
        );

        bill.setStatus(
                rs.getString("status")
        );

        Timestamp createdAt =
                rs.getTimestamp("created_at");

        if (createdAt != null) {

            bill.setCreatedAt(
                    createdAt.toLocalDateTime()
            );

        }

        Customer customer = new Customer();

        customer.setId(
                rs.getLong("customer_id")
        );

        customer.setCustomerCode(
                rs.getString("customer_code")
        );

        customer.setFullName(
                rs.getString("full_name")
        );

        bill.setCustomer(customer);

        return bill;
    }

    /**
     * ==========================================================
     * Bill Item Mapper
     * ==========================================================
     */
    private BillItem mapBillItem(ResultSet rs)
            throws SQLException {

        BillItem item = new BillItem();

        item.setId(
                rs.getLong("id")
        );

        item.setBillId(
                rs.getLong("bill_id")
        );

        item.setProductId(
                rs.getInt("product_id")
        );

        item.setQuantity(
                rs.getInt("quantity")
        );

        item.setUnitPrice(
                rs.getBigDecimal("unit_price")
        );

        item.setLineTotal(
                rs.getBigDecimal("line_total")
        );

        Product product = new Product();

        product.setId(
                rs.getInt("product_id")
        );

        product.setProductCode(
                rs.getString("product_code")
        );

        product.setProductName(
                rs.getString("product_name")
        );

        item.setProduct(product);

        return item;

    }

    public BigDecimal getTotalRevenue() {

        String sql = """
                SELECT COALESCE(SUM(total_amount), 0)
                FROM bills
                """;

        try (
                Connection connection = DBConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql);
                ResultSet resultSet = statement.executeQuery()
        ) {

            if (resultSet.next()) {
                return resultSet.getBigDecimal(1);
            }

        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        return BigDecimal.ZERO;
    }

    public int getTotalBills() {

        String sql = """
                SELECT COUNT(*)
                FROM bills
                """;

        try (
                Connection connection = DBConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql);
                ResultSet resultSet = statement.executeQuery()
        ) {

            if (resultSet.next()) {
                return resultSet.getInt(1);
            }

        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        return 0;
    }
}