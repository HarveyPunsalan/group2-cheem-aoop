/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.motorph.database.execution;

import java.util.logging.Logger;
import java.util.logging.Level;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author 63909
 */

/**
 * Utility class for executing parameterized SQL queries and updates.
 * Encapsulates logic for preparing statements, setting parameters, and mapping results.
 * Promotes reusability and reduces boilerplate JDBC code in DAO implementations.
 */
public class SQLExecutor {
    
    private final Connection connection;
    private static final Logger LOGGER = Logger.getLogger(SQLExecutor.class.getName());
    
    /**
     * Constructs a new SQLExecutor with a given JDBC {@code Connection}.
     *
     * @param connection the active JDBC connection to be used for execution
     */
    public SQLExecutor(Connection connection) {
        this.connection = connection;
    }
    
    /**
     * Executes a parameterized SELECT query and maps the results using the provided {@link ResultSetMapper}.
     *
     * @param script the SQL script to execute
     * @param params the list of parameters to bind to the query
     * @param mapper the mapping function to convert each row to a Java object
     * @param <T>    the type of objects in the result list
     * @return a list of mapped objects from the result set
     * @throws SQLException if a database access error occurs
     */
    public <T> List<T> executeQuery(Script script, List<Object> params, ResultSetMapper<T> mapper) throws SQLException {
        // 🔒 Validate parameter count
        validateParameters(script.toString(), params);
        
        List<T> results = new ArrayList<>();

        try (PreparedStatement preparedStatement = connection.prepareStatement(script.toString())) {
            // ✅ Only set parameters if there are any
            if (!params.isEmpty()) {
                setParameters(preparedStatement, params);
            }

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    results.add(mapper.map(resultSet));
                }
            }
        }

        return results;
    }

    /**
     * Executes a SELECT query with no parameters.
     *
     * @param script the SQL script to execute
     * @param mapper the mapping function to convert each row to a Java object
     * @param <T>    the type of objects in the result list
     * @return a list of mapped objects from the result set
     * @throws SQLException if a database access error occurs
     */
    public <T> List<T> executeQuery(Script script, ResultSetMapper<T> mapper) throws SQLException {
        return executeQuery(script, List.of(), mapper);
    }  

    /**
     * Executes an INSERT, UPDATE, or DELETE statement with parameters.
     *
     * @param script the SQL statement with placeholders
     * @param params the list of parameters to bind to the statement
     * @return number of affected rows
     */
    public int executeUpdate(Script script, List<Object> params) {
        // 🔒 Validate parameter count
        validateParameters(script.toString(), params);
        
        try (PreparedStatement preparedStatement = connection.prepareStatement(script.toString())) {
            // ✅ Only set parameters if there are any
            if (!params.isEmpty()) {
                setParameters(preparedStatement, params.toArray());                
            }
            
            return preparedStatement.executeUpdate();
            
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Failed to execute update: " + script.toString(), e);
            throw new RuntimeException("Database update failed: " + script.name(), e);
        }
    }
    
    /**
     * Executes an INSERT, UPDATE, or DELETE statement with no parameters.
     *
     * @param script the SQL statement to execute
     * @return number of affected rows
     */
    public int executeUpdate(Script script) {
        return executeUpdate(script, List.of());
    }

    /**
     * Sets parameters into the {@link PreparedStatement}.
     *
     * @param stmt   the {@code PreparedStatement} to bind values to
     * @param params the parameters to set
     * @throws SQLException if parameter binding fails
     */
    private static void setParameters(PreparedStatement stmt, Object... params) throws SQLException {
        for (int i = 0; i < params.length; i++) {
            stmt.setObject(i + 1, params[i]);
        }
    }
    
    /**
     * Counts the number of '?' placeholders in a SQL string.
     *
     * @param sql the SQL string
     * @return the number of placeholders found
     */
    private int countPlaceholders(String sql) {
    int count = 0;
        for (char c : sql.toCharArray()) {
            if (c == '?') count++;
        }
        return count;
    }
    
    /**
     * Validates that the number of provided parameters matches the number of placeholders in the SQL.
     *
     * @param sql    the SQL string
     * @param params the list of parameters to validate
     * @throws IllegalArgumentException if the counts do not match
     */
    private void validateParameters(String sql, List<Object> params) {
        int expected = countPlaceholders(sql);
        if (expected != params.size()) {
            throw new IllegalArgumentException(
                String.format("Parameter mismatch: expected %d but got %d", expected, params.size())
            );
        }
    }
}
