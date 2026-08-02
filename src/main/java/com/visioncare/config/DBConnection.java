package com.visioncare.config;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Handles database connection for the VisionCare ERP application.
 *
 * This class reads database configuration from
 * database.properties and provides a reusable
 * database connection.
 */
public final class DBConnection {

    private static final Properties properties = new Properties();

    static {
        try (InputStream inputStream =
                     DBConnection.class.getClassLoader()
                             .getResourceAsStream("database.properties")) {
            if (inputStream == null) {
                throw new RuntimeException("database.properties file not found.");
            }
            properties.load(inputStream);
            Class.forName(properties.getProperty("db.driver"));
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException("Failed to initialize database configuration.", e);
        }
    }

    /**
     * Private constructor to prevent object creation.
     */
    private DBConnection() {
    }

    /**
     * Returns a new database connection.
     *
     * @return JDBC Connection
     * @throws SQLException if connection fails
     */
    public static Connection getConnection() throws SQLException {

        return DriverManager.getConnection(
                properties.getProperty("db.url"),
                properties.getProperty("db.username"),
                properties.getProperty("db.password")
        );
    }
}