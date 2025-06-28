/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.motorph.database.connection;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Logger;
import java.util.logging.Level;

/**
 *
 * @author 63909
 */

/**
 * Provides utility methods for connecting to the MotorPH Payroll database.
 * Acts as a service wrapper around {@link DatabaseConnector} to simplify
 * access to database connections and testing functionality.
 */
public class DatabaseService {
    
    private static final Logger LOGGER = Logger.getLogger(DatabaseService.class.getName());

    /**
     * Establishes a JDBC connection to the MotorPH Payroll database.
     * Delegates the actual connection logic to {@link DatabaseConnector}.
     *
     * @return a live Connection instance
     * @throws RuntimeException if connection fails
     */    
    public static Connection connectToMotorPH() {
        try {
            return DatabaseConnector.connect();
        } catch (SQLException e) {
            logConnectionFailure(e);
            throw new RuntimeException("Database connection failed: " + e.getMessage(), e);
        }
    }
    
    /**
     * Logs database connection failures using java.util.logging.
     *
     * @param e SQLException to be logged
     */
    public static void logConnectionFailure(SQLException e) {
        LOGGER.log(Level.SEVERE, "Failed to connect to the database", e);
    }

    /**
     * Tests the database connection and prints the result to the console.
     * Useful for verifying connection settings during development.
     */
    public static void testConnection() {
        try (Connection connection = connectToMotorPH()) {
            System.out.println("Connected to MotorPH Payroll DB.");
        } catch (SQLException e) {
            System.err.println("Connection failed: " + e.getMessage());
        }
    }
    
    // ================================================================
    // 🔁 TRANSACTION MANAGEMENT METHODS
    // ================================================================

    /**
     * Begins a manual transaction by disabling auto-commit mode.
     */
    public static void beginTransaction(Connection connection) throws SQLException {
        if (connection != null) {
            connection.setAutoCommit(false);
        }
    }

    /**
     * Commits all statements executed since the last beginTransaction.
     */
    public static void commitTransaction(Connection connection) throws SQLException {
        if (connection != null) {
            connection.commit();
            connection.setAutoCommit(true); // restore default behavior
        }
    }

    /**
     * Rolls back all statements executed since the last beginTransaction.
     */
    public static void rollbackTransaction(Connection connection) throws SQLException {
        if (connection != null) {
            connection.rollback();
            connection.setAutoCommit(true); // restore default behavior
        }
    }
}
