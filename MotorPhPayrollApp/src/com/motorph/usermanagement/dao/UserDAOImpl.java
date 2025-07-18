/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.motorph.usermanagement.dao;

import com.motorph.usermanagement.model.User;
import com.motorph.usermanagement.exception.DataAccessException;
import com.motorph.database.connection.DatabaseService;

import java.sql.*;
import java.util.Optional;
import java.util.logging.Logger;
import java.util.logging.Level;
import com.motorph.database.execution.EntityMapper;

/**
 * DAO implementation focused on login functionality only.
 * Handles only the database operations needed for user authentication.
 * 
 * @author Harvey 
 */
public class UserDAOImpl implements UserDAO {
    private static final Logger logger = Logger.getLogger(UserDAOImpl.class.getName());
    private final Connection connection;
    
    // SQL queries for essential user operations only
    private static final String SELECT_USER_BY_ID_SQL = 
        "SELECT user_id, username, password_hashed, employee_id, role_id, account_created, last_login, is_active FROM system_user WHERE user_id = ?";
    
    private static final String SELECT_USER_BY_USERNAME_SQL = 
        "SELECT user_id, username, password_hashed, employee_id, role_id, account_created, last_login, is_active FROM system_user WHERE username = ?";
    
    private static final String UPDATE_LAST_LOGIN_SQL = 
        "UPDATE system_user SET last_login = NOW() WHERE user_id = ?";
    
    private static final String UPDATE_PASSWORD_HASH_SQL = 
        "UPDATE system_user SET password_hashed = ? WHERE user_id = ?";
    
    /**
     * ResultSetMapper for converting database rows to User objects.
     * Centralizes the mapping logic for consistency across all query methods.
     */
    private static final EntityMapper<User> USER_MAPPER = resultSet -> {
        User user = new User();
        user.setUserId(resultSet.getInt("user_id"));
        user.setUsername(resultSet.getString("username"));
        user.setPasswordHashed(resultSet.getString("password_hashed"));   
        user.setEmployeeId(resultSet.getInt("employee_id"));  
        user.setRoleId(resultSet.getInt("role_id"));
        
        // Handle potential null timestamps from database
        Timestamp accountCreated = resultSet.getTimestamp("account_created");
        if (accountCreated != null) {
            user.setAccountCreated(accountCreated);
        }
        
        Timestamp lastLogin = resultSet.getTimestamp("last_login");
        if (lastLogin != null) {
            user.setLastLogin(lastLogin);
        }
        
        user.setActive(resultSet.getBoolean("is_active"));
        return user;
    };
    
    /**
     * Constructor initializes database connection.
     * Uses the existing DatabaseService for connection management.
     */
    public UserDAOImpl() {
        try {
            this.connection = DatabaseService.connectToMotorPH();
            logger.info("UserDAOImpl initialized successfully with database connection for login functionality");
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Failed to initialize UserDAOImpl", e);
            throw new RuntimeException("Unable to establish database connection for UserDAO", e);
        }
    }
    
    /**
     * Constructor with external connection (useful for testing or transaction management)
     * 
     * @param connection the JDBC connection to be used by this DAO
     */
    public UserDAOImpl(Connection connection) {
        this.connection = connection;
    }
    
    @Override
    public Optional<User> findById(int userId) throws DataAccessException {
        try (PreparedStatement stmt = connection.prepareStatement(SELECT_USER_BY_ID_SQL)) {
            stmt.setInt(1, userId);
            
            try (ResultSet resultSet = stmt.executeQuery()) {
                if (resultSet.next()) {
                    User user = USER_MAPPER.map(resultSet);
                    logger.fine(() -> "Found user by ID: " + userId);
                    return Optional.of(user);
                } else {
                    logger.fine(() -> "No user found with ID: " + userId);
                    return Optional.empty();
                }
            }
            
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Database error while finding user by ID: " + userId, e);
            throw new DataAccessException("Failed to find user by ID", e);
        }
    }
    
    @Override
    public Optional<User> findByUsername(String username) throws DataAccessException {
        try (PreparedStatement stmt = connection.prepareStatement(SELECT_USER_BY_USERNAME_SQL)) {
            stmt.setString(1, username);
            
            try (ResultSet resultSet = stmt.executeQuery()) {
                if (resultSet.next()) {
                    User user = USER_MAPPER.map(resultSet);
                    logger.fine(() -> "Found user by username: " + username);
                    return Optional.of(user);
                } else {
                    logger.fine(() -> "No user found with username: " + username);
                    return Optional.empty();
                }
            }
            
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Database error while finding user by username: " + username, e);
            throw new DataAccessException("Failed to find user by username", e);
        }
    }
    
    @Override
    public boolean updateLastLogin(int userId) throws DataAccessException {
        try (PreparedStatement stmt = connection.prepareStatement(UPDATE_LAST_LOGIN_SQL)) {
            stmt.setInt(1, userId);
            
            int affectedRows = stmt.executeUpdate();
            boolean updateSuccessful = affectedRows > 0;
            
            if (updateSuccessful) {
                logger.fine(() -> "Updated last login timestamp for user ID: " + userId);
            }
            
            return updateSuccessful;
            
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Database error while updating last login for user: " + userId, e);
            throw new DataAccessException("Failed to update last login timestamp", e);
        }
    }
    
    @Override
    public boolean updatePasswordHash(int userId, String hashedPassword) throws DataAccessException {
        try (PreparedStatement stmt = connection.prepareStatement(UPDATE_PASSWORD_HASH_SQL)) {
            stmt.setString(1, hashedPassword);
            stmt.setInt(2, userId);
            
            int affectedRows = stmt.executeUpdate();
            boolean updateSuccessful = affectedRows > 0;
            
            if (updateSuccessful) {
                logger.fine(() -> "Updated password hash for user ID: " + userId);
            }
            
            return updateSuccessful;
            
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Database error while updating password hash for user: " + userId, e);
            throw new DataAccessException("Failed to update password hash", e);
        }
    }
}