/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.motorph.database.execution;

//import com.motorph.common.mapper.EntityMapper;
import com.motorph.common.mapper.ModelMapper;
import com.motorph.database.execution.script.Script;
import java.util.logging.Logger;
import java.util.logging.Level;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
     * Executes a parameterized SELECT query and maps the results using the provided {@link EntityMapper}.
     *
     * @param script the SQL script to execute
     * @param params the list of parameters to bind to the query
     * @param mapper the mapping function to convert each row to a Java object
     * @param <T>    the type of objects in the result list
     * @return a list of mapped objects from the result set
     * @throws SQLException if a database access error occurs
     */
    public <T> List<T> executeQuery(Script script, List<Object> params, EntityMapper<T> mapper) throws SQLException {
        // 🔒 Validate parameter count
        validateParameters(script.getQuery(), params);
        logQuery(script, params);
        
        List<T> results = new ArrayList<>();
        
        try (PreparedStatement preparedStatement = connection.prepareStatement(script.getQuery())) {
            // ✅ Only set parameters if there are any
            if (!params.isEmpty()) {
                setParameters(preparedStatement, params.toArray());
            }

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    results.add(mapper.map(resultSet));
                }
            }
        }
        return results;
    }
    
    public <T> List<T> executeQuery(Script script, List<Object> params, ModelMapper<T> mapper) throws SQLException {
        // 🔒 Validate parameter count
        validateParameters(script.getQuery(), params);
        logQuery(script, params);
        
        List<T> results = new ArrayList<>();

        try (PreparedStatement preparedStatement = connection.prepareStatement(script.getQuery())) {
            // ✅ Only set parameters if there are any
            if (!params.isEmpty()) {
                setParameters(preparedStatement, params.toArray());
            }

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    results.add(mapper.map(resultSet));
                }
            } catch (SQLException e) {
                throw new RuntimeException("Query failed: " + script.getQuery(), e);
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
    public <T> List<T> executeQuery(Script script, EntityMapper<T> mapper) throws SQLException {
        return executeQuery(script, List.of(), mapper);
    }

    public <T> List<T> executeQuery(Script script, ModelMapper<T> mapper) throws SQLException {
        return executeQuery(script, List.of(), mapper);
    } 
    
    // ✅ NEW: Query for single object
    public <T> Optional<T> queryForObject(Script script, List<Object> params, EntityMapper<T> mapper) throws SQLException {
        List<T> results = executeQuery(script, params, mapper);
        return results.isEmpty() ? Optional.empty() : Optional.of(results.get(0));
    }
    
    public <T> Optional<T> queryForObject(Script script, List<Object> params, ModelMapper<T> mapper) throws SQLException {
        List<T> results = executeQuery(script, params, mapper);
        return results.isEmpty() ? Optional.empty() : Optional.of(results.get(0));
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
        validateParameters(script.getQuery(), params);
        logQuery(script, params);
        
        try (PreparedStatement preparedStatement = connection.prepareStatement(script.getQuery())) {
            // ✅ Only set parameters if there are any
            if (!params.isEmpty()) {
                setParameters(preparedStatement, params.toArray());                
            }
            
            return preparedStatement.executeUpdate();
            
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Failed to execute update: " + script.getQuery(), e);
            throw new RuntimeException("Database update failed: " + script.getQuery(), e);
        }
    }
    
    public int executeUpdate(Script script, PreparedStatementSetter setter) {           
        try (PreparedStatement ps = connection.prepareStatement(script.getQuery())) {            
            setter.set(ps); 
            return ps.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Failed to execute update: " + script.getQuery(), e);
            throw new RuntimeException("Database update failed: " + script.getQuery(), e);
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
    
    // ✅ NEW: Batch update
    public int[] batchUpdate(Script script, List<List<Object>> batchParams) throws SQLException {
        logBatch(script, batchParams.size());

        try (PreparedStatement ps = connection.prepareStatement(script.getQuery())) {
            for (List<Object> params : batchParams) {
                setParameters(ps, params.toArray());
                ps.addBatch();
            }
            return ps.executeBatch();
        }
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
    
    private void logQuery(Script script, List<Object> params) {
        LOGGER.fine(() -> String.format("Executing SQL [%s]: %s with params: %s", script.getQuery(), script, params));
    }

    private void logBatch(Script script, int batchSize) {
        LOGGER.fine(() -> String.format("Executing batch SQL [%s] with %d sets of parameters", script.getQuery(), batchSize));
    }
    
    @FunctionalInterface
    public interface PreparedStatementSetter {
        void set(PreparedStatement stmt) throws SQLException;
    }
}
