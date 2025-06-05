/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.motorph.database.connection;

import java.sql.Connection;
import java.sql.SQLException;

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

    /**
     * Establishes a JDBC connection to the MotorPH Payroll database.
     * Delegates the actual connection logic to {@link DatabaseConnector}.
     *
     * @return a valid {@code Connection} object
     * @throws SQLException if the connection cannot be established
     */    
    public static Connection connectToMotorPH() throws SQLException {
        return DatabaseConnector.connect();
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
}
