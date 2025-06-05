/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.motorph.database.connection;

import com.motorph.database.config.DatabaseConfigReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author 63909
 */

/**
 * Provides JDBC connections using configuration from {@link DatabaseConfigReader}.
 * Acts as a utility class to centralize connection instantiation logic.
 */
public class DatabaseConnector {

    /**
     * Establishes and returns a JDBC {@link Connection} to the MotorPH database.
     * It retrieves the configuration details (URL, username, password) from the
     * singleton {@link DatabaseConfigReader}.
     *
     * @return a valid {@code Connection} to the database
     * @throws SQLException if establishing the connection fails
     */
    public static Connection connect() throws SQLException {
        DatabaseConfigReader config = DatabaseConfigReader.getInstance();
        return DriverManager.getConnection(config.getUrl(),config.getUsername(),config.getPassword());
    }
}
