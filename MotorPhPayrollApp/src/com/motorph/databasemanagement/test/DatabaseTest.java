/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.motorph.databasemanagement.test;

import com.motorph.databasemanagement.connection.DatabaseConnector;
import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author 63909
 */

/**
 * Test class to verify the database connection using {@link DatabaseConnector}.
 * Intended for manual validation during development to ensure connectivity.
 */
public class DatabaseTest {
    
    /**
     * Main method that attempts to connect to the database and prints the result.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        try (Connection connection = DatabaseConnector.connect()) {
            if (connection != null && !connection.isClosed()) {
                System.out.println("Connected to the database successfully.");
            } else {
                System.out.println("Connection failed.");
            }
        } catch (SQLException e) {
            System.err.println("Error during database connection test:");
            e.printStackTrace();
        }
    }
}

