/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.motorph.usermanagement.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Logger;
import java.util.logging.Level;


/**
 * Utility class providing helper methods for JDBC operations
 * This includes methods for safely closing database resources and handling transactions
 * 
 * @author Harvey
 */

public class JDBCUtils {
    private static final Logger logger = Logger.getLogger(JDBCUtils.class.getName());
    
    // Private constructor to prevent instantiation
    private JDBCUtils() {
        throw new IllegalStateException("Utility class should not be instantiated");
    }
    
    /**
     * Safely closes a JDBC Connection without throwing exceptions.
     * Logs any errors that occur during closing.
     * 
     * @param connection The Connection to close, can be null
     */
    public static void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
                logger.fine("Database connection closed successfully");
            } catch (SQLException e) {
                logger.log(Level.WARNING, "Error closing database connection", e);
            }
        }
    }
    
    /**
     * Safely closes a JDBC PreparedStatement without throwing exceptions.
     * 
     * @param statement The PreparedStatement to close, can be null
     */
    public static void closeStatement(PreparedStatement statement) {
        if (statement != null) {
            try {
                statement.close();
                logger.fine("PreparedStatement closed successfully");
            } catch (SQLException e) {
                logger.log(Level.WARNING, "Error closing PreparedStatement", e);
            }
        }
    }
    
    /**
     * Safely closes a JDBC Statement without throwing exceptions.
     * 
     * @param statement The Statement to close, can be null
     */
    public static void closeStatement(Statement statement) {
        if (statement != null) {
            try {
                statement.close();
                logger.fine("Statement closed successfully");
            } catch (SQLException e) {
                logger.log(Level.WARNING, "Error closing Statement", e);
            }
        }
    }
    
    /**
     * Safely closes a JDBC ResultSet without throwing exceptions.
     * 
     * @param resultSet The ResultSet to close, can be null
     */
    public static void closeResultSet(ResultSet resultSet) {
        if (resultSet != null) {
            try {
                resultSet.close();
                logger.fine("ResultSet closed successfully");
            } catch (SQLException e) {
                logger.log(Level.WARNING, "Error closing ResultSet", e);
            }
        }
    }
    
    /**
     * Closes multiple JDBC resources in the correct order: ResultSet, Statement, Connection.
     * This is a convenience method for cleaning up all resources at once.
     * 
     * @param resultSet The ResultSet to close, can be null
     * @param statement The Statement to close, can be null
     * @param connection The Connection to close, can be null
     */
    public static void closeResources(ResultSet resultSet, PreparedStatement statement, Connection connection) {
        closeResultSet(resultSet);
        closeStatement(statement);
        closeConnection(connection);
    }
    
    /**
     * Closes multiple JDBC resources - overloaded for regular Statement.
     * 
     * @param resultSet The ResultSet to close, can be null
     * @param statement The Statement to close, can be null
     * @param connection The Connection to close, can be null
     */
    public static void closeResources(ResultSet resultSet, Statement statement, Connection connection) {
        closeResultSet(resultSet);
        closeStatement(statement);
        closeConnection(connection);
    }
    
    /**
     * Rolls back a transaction safely without throwing exceptions.
     * Used in exception handling to ensure database consistency.
     * 
     * @param connection The Connection to rollback, can be null
     */
    public static void rollbackTransaction(Connection connection) {
        if (connection != null) {
            try {
                connection.rollback();
                logger.info("Transaction rolled back successfully");
            } catch (SQLException e) {
                logger.log(Level.SEVERE, "Error rolling back transaction", e);
            }
        }
    }
    
    /**
     * Commits a transaction safely and logs the result.
     * 
     * @param connection The Connection to commit
     * @throws SQLException if commit fails
     */
    public static void commitTransaction(Connection connection) throws SQLException {
        if (connection != null) {
            connection.commit();
            logger.info("Transaction committed successfully");
        }
    }
    
    /**
     * Sets up a connection for transaction processing by disabling auto-commit.
     * 
     * @param connection The Connection to configure
     * @throws SQLException if configuration fails
     */
    public static void beginTransaction(Connection connection) throws SQLException {
        if (connection != null) {
            connection.setAutoCommit(false);
            logger.fine("Transaction started - auto-commit disabled");
        }
    }
    
    /**
     * Restores auto-commit mode on a connection after transaction processing.
     * 
     * @param connection The Connection to restore
     */
    public static void endTransaction(Connection connection) {
        if (connection != null) {
            try {
                connection.setAutoCommit(true);
                logger.fine("Transaction ended - auto-commit restored");
            } catch (SQLException e) {
                logger.log(Level.WARNING, "Error restoring auto-commit mode", e);
            }
        }
    }
    
    /**
     * Checks if a connection is valid and not closed.
     * 
     * @param connection The Connection to check
     * @return true if connection is valid, false otherwise
     */
    public static boolean isConnectionValid(Connection connection) {
        try {
            return connection != null && !connection.isClosed() && connection.isValid(5);
        } catch (SQLException e) {
            logger.log(Level.WARNING, "Error checking connection validity", e);
            return false;
        }
    }
    
    /**
     * Safely sets parameters in a PreparedStatement with type checking.
     * 
     * @param statement The PreparedStatement to set parameters on
     * @param parameterIndex The parameter index (1-based)
     * @param value The value to set
     * @throws SQLException if parameter setting fails
     */
    public static void setParameter(PreparedStatement statement, int parameterIndex, Object value) throws SQLException {
        if (value == null) {
            statement.setNull(parameterIndex, java.sql.Types.NULL);
        } else {
            statement.setObject(parameterIndex, value);
        }
        logger.finest(() -> "Parameter " + parameterIndex + " set to: " + value);
    }
    
    /**
     * Gets a string value from ResultSet with null checking.
     * 
     * @param resultSet The ResultSet to read from
     * @param columnName The column name
     * @return The string value or null if NULL in database
     * @throws SQLException if column access fails
     */
    public static String getString(ResultSet resultSet, String columnName) throws SQLException {
        String value = resultSet.getString(columnName);
        return resultSet.wasNull() ? null : value;
    }
    
    /**
     * Gets an integer value from ResultSet with null checking.
     * 
     * @param resultSet The ResultSet to read from
     * @param columnName The column name
     * @return The integer value or null if NULL in database
     * @throws SQLException if column access fails
     */
    public static Integer getInteger(ResultSet resultSet, String columnName) throws SQLException {
        int value = resultSet.getInt(columnName);
        return resultSet.wasNull() ? null : value;
    }
    
    /**
     * Gets a boolean value from ResultSet with null checking.
     * 
     * @param resultSet The ResultSet to read from
     * @param columnName The column name
     * @return The boolean value or null if NULL in database
     * @throws SQLException if column access fails
     */
    public static Boolean getBoolean(ResultSet resultSet, String columnName) throws SQLException {
        boolean value = resultSet.getBoolean(columnName);
        return resultSet.wasNull() ? null : value;
    }
}
