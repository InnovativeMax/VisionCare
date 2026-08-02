package com.visioncare.dao;

import com.visioncare.model.User;
import com.visioncare.config.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

/**
 * ==========================================================
 * VisionCare ERP
 * File        : UserDAO.java
 * Description : User Data Access Object
 * Version     : 1.0
 * ==========================================================
 */
public class UserDAO {

    /*
     * ==========================================================
     * Find User By Username
     * ==========================================================
     */

    public User findByUsername(String username) {

        String sql = """
                SELECT
                    id,
                    user_code,
                    full_name,
                    username,
                    password_hash,
                    role_id,
                    theme,
                    must_change_password,
                    last_login,
                    is_active,
                    created_at,
                    created_by,
                    updated_at,
                    updated_by
                FROM users
                WHERE username = ?
                  AND is_active = TRUE
                """;

        try (
                Connection connection = DBConnection.getConnection();
                PreparedStatement statement =
                        connection.prepareStatement(sql)
        ) {

            statement.setString(1, username);

            try (ResultSet rs = statement.executeQuery()) {

                if (rs.next()) {

                    User user = new User();

                    user.setId(rs.getLong("id"));
                    user.setUserCode(rs.getString("user_code"));
                    user.setFullName(rs.getString("full_name"));
                    user.setUsername(rs.getString("username"));
                    user.setPassword(rs.getString("password_hash"));
                    user.setRoleId(rs.getLong("role_id"));
                    user.setTheme(rs.getString("theme"));
                    user.setMustChangePassword(
                            rs.getBoolean("must_change_password"));
                    user.setLastLogin(
                            rs.getTimestamp("last_login"));
                    user.setActive(
                            rs.getBoolean("is_active"));
                    user.setCreatedAt(
                            rs.getTimestamp("created_at"));
                    user.setCreatedBy(
                            rs.getLong("created_by"));
                    user.setUpdatedAt(
                            rs.getTimestamp("updated_at"));
                    user.setUpdatedBy(
                            rs.getLong("updated_by"));

                    return user;

                }

            }

        } catch (SQLException exception) {

            exception.printStackTrace();

        }

        return null;

    }

    /*
     * ==========================================================
     * Update Last Login
     * ==========================================================
     */

    public void updateLastLogin(Long userId) {

        String sql = """
                UPDATE users
                SET last_login = ?
                WHERE id = ?
                """;

        try (
                Connection connection = DBConnection.getConnection();
                PreparedStatement statement =
                        connection.prepareStatement(sql)
        ) {

            statement.setTimestamp(
                    1,
                    new Timestamp(System.currentTimeMillis())
            );

            statement.setLong(2, userId);

            statement.executeUpdate();

        } catch (SQLException exception) {

            exception.printStackTrace();

        }

    }

}