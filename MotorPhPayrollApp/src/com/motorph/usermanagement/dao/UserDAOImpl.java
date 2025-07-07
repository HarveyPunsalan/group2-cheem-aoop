/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.motorph.usermanagement.dao;

import com.motorph.usermanagement.model.User;
import com.motorph.usermanagement.exception.UserNotFoundException;
import com.motorph.usermanagement.exception.DuplicateUserException;
import com.motorph.usermanagement.exception.DataAccessException;
import com.motorph.database.connection.DatabaseService;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;
import java.util.logging.Level;
import com.motorph.database.execution.EntityMapper;

/**
 * Handles all database operations related to user management
 * @author Harvey 
 */
public class UserDAOImpl implements UserDAO {
    private static final Logger logger = Logger.getLogger(UserDAOImpl.class.getName());
    private final Connection connection;
    
    // SQL queries for user operations - following the database schema structure
    private static final String INSERT_USER_SQL = 
        "INSERT INTO system_user (username, password_hashed, employee_id, role_id, account_created, is_active) VALUES (?, ?, ?, ?, NOW(), ?)";
    
    private static final String SELECT_USER_BY_ID_SQL = 
        "SELECT user_id, username, password_hashed, employee_id, role_id, account_created, last_login, is_active FROM system_user WHERE user_id = ?";
    
    private static final String SELECT_USER_BY_USERNAME_SQL = 
        "SELECT user_id, username, password_hashed, employee_id, role_id, account_created, last_login, is_active FROM system_user WHERE username = ?";
    
    private static final String SELECT_USER_BY_EMPLOYEE_ID_SQL = 
        "SELECT user_id, username, password_hashed, employee_id, role_id, account_created, last_login, is_active FROM system_user WHERE employee_id = ?";
    
    private static final String SELECT_ALL_USERS_SQL = 
        "SELECT user_id, username, password_hashed, employee_id, role_id, account_created, last_login, is_active FROM system_user ORDER BY username";
    
    private static final String SELECT_USERS_BY_ROLE_SQL = 
        "SELECT user_id, username, password_hashed, employee_id, role_id, account_created, last_login, is_active FROM system_user WHERE role_id = ? ORDER BY username";
    
    private static final String SELECT_ACTIVE_USERS_SQL = 
        "SELECT user_id, username, password_hashed, employee_id, role_id, account_created, last_login, is_active FROM system_user WHERE is_active = TRUE ORDER BY username";
    
    private static final String UPDATE_USER_SQL = 
        "UPDATE system_user SET username = ?, employee_id = ?, role_id = ?, is_active = ? WHERE user_id = ?";
    
    private static final String UPDATE_PASSWORD_SQL = 
        "UPDATE system_user SET password_hashed = ? WHERE user_id = ?";
    
    private static final String UPDATE_LAST_LOGIN_SQL = 
        "UPDATE system_user SET last_login = NOW() WHERE user_id = ?";
    
    private static final String DEACTIVATE_USER_SQL = 
        "UPDATE system_user SET is_active = FALSE WHERE user_id = ?";
    
    private static final String ACTIVATE_USER_SQL = 
        "UPDATE system_user SET is_active = TRUE WHERE user_id = ?";
    
    private static final String DELETE_USER_SQL = 
        "DELETE FROM system_user WHERE user_id = ?";
    
    private static final String CHECK_USERNAME_EXISTS_SQL = 
        "SELECT COUNT(*) FROM system_user WHERE username = ?";
    
    private static final String CHECK_EMPLOYEE_HAS_ACCOUNT_SQL = 
        "SELECT COUNT(*) FROM system_user WHERE employee_id = ?";
    
    private static final String COUNT_USERS_SQL = 
        "SELECT COUNT(*) FROM system_user";
    
    private static final String COUNT_ACTIVE_USERS_SQL = 
        "SELECT COUNT(*) FROM system_user WHERE is_active = TRUE";
    
    private static final String COUNT_USERS_BY_ROLE_SQL = 
        "SELECT COUNT(*) FROM system_user WHERE role_id = ?";

    private static final String COUNT_INACTIVE_USERS_SQL = 
        "SELECT COUNT(*) FROM system_user WHERE is_active = FALSE";
    
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
            logger.info("UserDAOImpl initialized successfully with database connection");
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
    public int createUser(User user) throws DuplicateUserException, DataAccessException {
        // First check if username already exists
        if (usernameExists(user.getUsername())) {
            throw new DuplicateUserException("Username '" + user.getUsername() + "' already exists in the system");
        }
        
        // Check if employee already has an account - CORRECTED: Now converts int to String for interface compatibility
        if (employeeHasAccount(String.valueOf(user.getEmployeeId()))) {
            throw new DuplicateUserException("Employee ID '" + user.getEmployeeId() + "' already has an associated user account");
        }
        
        try (PreparedStatement stmt = connection.prepareStatement(INSERT_USER_SQL, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getPasswordHashed());
            stmt.setInt(3, user.getEmployeeId());
            stmt.setInt(4, user.getRoleId());
            stmt.setBoolean(5, user.isActive());
            
            int affectedRows = stmt.executeUpdate();
            
            if (affectedRows == 0) {
                throw new DataAccessException("Failed to create user - no rows affected");
            }
            
            // Retrieve the generated user ID
            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int generatedUserId = generatedKeys.getInt(1);
                    user.setUserId(generatedUserId);
                    logger.info(() -> "Successfully created user with ID: " + generatedUserId + ", username: " + user.getUsername());
                    return generatedUserId;
                } else {
                    throw new DataAccessException("Failed to retrieve generated user ID after insertion");
                }
            }
            
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Database error while creating user: " + user.getUsername(), e);
            throw new DataAccessException("Failed to create user due to database error", e);
        }
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
    public Optional<User> findByEmployeeId(String employeeId) throws DataAccessException {
        try (PreparedStatement stmt = connection.prepareStatement(SELECT_USER_BY_EMPLOYEE_ID_SQL)) {
            // Convert String employeeId to int for database query
            int empId = Integer.parseInt(employeeId);
            stmt.setInt(1, empId);
            
            try (ResultSet resultSet = stmt.executeQuery()) {
                if (resultSet.next()) {
                    User user = USER_MAPPER.map(resultSet);
                    logger.fine(() -> "Found user by employee ID: " + employeeId);
                    return Optional.of(user);
                } else {
                    logger.fine(() -> "No user found with employee ID: " + employeeId);
                    return Optional.empty();
                }
            }
            
        } catch (NumberFormatException e) {
            logger.log(Level.WARNING, "Invalid employee ID format: " + employeeId, e);
            throw new DataAccessException("Invalid employee ID format", e);
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Database error while finding user by employee ID: " + employeeId, e);
            throw new DataAccessException("Failed to find user by employee ID", e);
        }
    }
    
    @Override
    public List<User> findAll() throws DataAccessException {
        List<User> users = new ArrayList<>();
        
        try (PreparedStatement stmt = connection.prepareStatement(SELECT_ALL_USERS_SQL);
             ResultSet resultSet = stmt.executeQuery()) {
            
            while (resultSet.next()) {
                users.add(USER_MAPPER.map(resultSet));
            }
            
            logger.info(() -> "Retrieved " + users.size() + " users from database");
            return users;
            
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Database error while retrieving all users", e);
            throw new DataAccessException("Failed to retrieve all users", e);
        }
    }
    
    @Override
    public List<User> findByRole(int roleId) throws DataAccessException {
        List<User> users = new ArrayList<>();
        
        try (PreparedStatement stmt = connection.prepareStatement(SELECT_USERS_BY_ROLE_SQL)) {
            stmt.setInt(1, roleId);
            
            try (ResultSet resultSet = stmt.executeQuery()) {
                while (resultSet.next()) {
                    users.add(USER_MAPPER.map(resultSet));
                }
            }
            
            logger.info(() -> "Retrieved " + users.size() + " users with role ID: " + roleId);
            return users;
            
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Database error while finding users by role: " + roleId, e);
            throw new DataAccessException("Failed to find users by role", e);
        }
    }
    
    @Override
    public List<User> findActiveUsers() throws DataAccessException {
        List<User> users = new ArrayList<>();
        
        try (PreparedStatement stmt = connection.prepareStatement(SELECT_ACTIVE_USERS_SQL);
             ResultSet resultSet = stmt.executeQuery()) {
            
            while (resultSet.next()) {
                users.add(USER_MAPPER.map(resultSet));
            }
            
            logger.info(() -> "Retrieved " + users.size() + " active users from database");
            return users;
            
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Database error while retrieving active users", e);
            throw new DataAccessException("Failed to retrieve active users", e);
        }
    }
    
    @Override
    public boolean updateUser(User user) throws UserNotFoundException, DataAccessException {
        // First verify the user exists
        if (!findById(user.getUserId()).isPresent()) {
            throw new UserNotFoundException("User with ID " + user.getUserId() + " not found");
        }
        
        try (PreparedStatement stmt = connection.prepareStatement(UPDATE_USER_SQL)) {
            stmt.setString(1, user.getUsername());
            stmt.setInt(2, user.getEmployeeId());
            stmt.setInt(3, user.getRoleId());
            stmt.setBoolean(4, user.isActive());
            stmt.setInt(5, user.getUserId());
            
            int affectedRows = stmt.executeUpdate();
            boolean updateSuccessful = affectedRows > 0;
            
            if (updateSuccessful) {
                logger.info(() -> "Successfully updated user with ID: " + user.getUserId());
            } else {
                logger.warning(() -> "No rows affected when updating user with ID: " + user.getUserId());
            }
            
            return updateSuccessful;
            
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Database error while updating user: " + user.getUserId(), e);
            throw new DataAccessException("Failed to update user", e);
        }
    }
    
    @Override
    public boolean updatePassword(int userId, String newPasswordHash) throws UserNotFoundException, DataAccessException {
        // First verify the user exists
        if (!findById(userId).isPresent()) {
            throw new UserNotFoundException("User with ID " + userId + " not found");
        }
        
        try (PreparedStatement stmt = connection.prepareStatement(UPDATE_PASSWORD_SQL)) {
            stmt.setString(1, newPasswordHash);
            stmt.setInt(2, userId);
            
            int affectedRows = stmt.executeUpdate();
            boolean updateSuccessful = affectedRows > 0;
            
            if (updateSuccessful) {
                logger.info(() -> "Successfully updated password for user ID: " + userId);
            } else {
                logger.warning(() -> "No rows affected when updating password for user ID: " + userId);
            }
            
            return updateSuccessful;
            
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Database error while updating password for user: " + userId, e);
            throw new DataAccessException("Failed to update user password", e);
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
    public boolean assignRole(int userId, int roleId) throws UserNotFoundException, DataAccessException {
        // First verify the user exists
        if (!findById(userId).isPresent()) {
            throw new UserNotFoundException("User with ID " + userId + " not found");
        }
        
        try (PreparedStatement stmt = connection.prepareStatement("UPDATE system_user SET role_id = ? WHERE user_id = ?")) {
            stmt.setInt(1, roleId);
            stmt.setInt(2, userId);
            
            int affectedRows = stmt.executeUpdate();
            boolean assignmentSuccessful = affectedRows > 0;
            
            if (assignmentSuccessful) {
                logger.info(() -> "Successfully assigned role " + roleId + " to user ID: " + userId);
            } else {
                logger.warning(() -> "No rows affected when assigning role to user ID: " + userId);
            }
            
            return assignmentSuccessful;
            
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Database error while assigning role to user: " + userId, e);
            throw new DataAccessException("Failed to assign role to user", e);
        }
    }
    
    @Override
    public boolean setUserStatus(int userId, boolean isActive) throws UserNotFoundException, DataAccessException {
        // First verify the user exists
        if (!findById(userId).isPresent()) {
            throw new UserNotFoundException("User with ID " + userId + " not found");
        }
        
        try (PreparedStatement stmt = connection.prepareStatement("UPDATE system_user SET is_active = ? WHERE user_id = ?")) {
            stmt.setBoolean(1, isActive);
            stmt.setInt(2, userId);
            
            int affectedRows = stmt.executeUpdate();
            boolean statusChangeSuccessful = affectedRows > 0;
            
            if (statusChangeSuccessful) {
                logger.info(() -> "Successfully set user status to " + isActive + " for user ID: " + userId);
            } else {
                logger.warning(() -> "No rows affected when setting user status for user ID: " + userId);
            }
            
            return statusChangeSuccessful;
            
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Database error while setting user status: " + userId, e);
            throw new DataAccessException("Failed to set user status", e);
        }
    }
    
    @Override
    public boolean deactivateUser(int userId) throws UserNotFoundException, DataAccessException {
        // First verify the user exists
        if (!findById(userId).isPresent()) {
            throw new UserNotFoundException("User with ID " + userId + " not found");
        }
        
        try (PreparedStatement stmt = connection.prepareStatement(DEACTIVATE_USER_SQL)) {
            stmt.setInt(1, userId);
            
            int affectedRows = stmt.executeUpdate();
            boolean deactivationSuccessful = affectedRows > 0;
            
            if (deactivationSuccessful) {
                logger.info(() -> "Successfully deactivated user with ID: " + userId);
            } else {
                logger.warning(() -> "No rows affected when deactivating user with ID: " + userId);
            }
            
            return deactivationSuccessful;
            
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Database error while deactivating user: " + userId, e);
            throw new DataAccessException("Failed to deactivate user", e);
        }
    }
    
    @Override
    public boolean activateUser(int userId) throws UserNotFoundException, DataAccessException {
        // First verify the user exists
        if (!findById(userId).isPresent()) {
            throw new UserNotFoundException("User with ID " + userId + " not found");
        }
        
        try (PreparedStatement stmt = connection.prepareStatement(ACTIVATE_USER_SQL)) {
            stmt.setInt(1, userId);
            
            int affectedRows = stmt.executeUpdate();
            boolean activationSuccessful = affectedRows > 0;
            
            if (activationSuccessful) {
                logger.info(() -> "Successfully activated user with ID: " + userId);
            } else {
                logger.warning(() -> "No rows affected when activating user with ID: " + userId);
            }
            
            return activationSuccessful;
            
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Database error while activating user: " + userId, e);
            throw new DataAccessException("Failed to activate user", e);
        }
    }
    
    @Override
    public boolean changePassword(int userId, String hashedPassword) throws UserNotFoundException, DataAccessException {
        // This delegates to updatePassword since they do the same thing
        return updatePassword(userId, hashedPassword);
    }
    
    @Override
    public boolean deleteUser(int userId) throws UserNotFoundException, DataAccessException {
        // First verify the user exists
        if (!findById(userId).isPresent()) {
            throw new UserNotFoundException("User with ID " + userId + " not found");
        }
        
        try (PreparedStatement stmt = connection.prepareStatement(DELETE_USER_SQL)) {
            stmt.setInt(1, userId);
            
            int affectedRows = stmt.executeUpdate();
            boolean deletionSuccessful = affectedRows > 0;
            
            if (deletionSuccessful) {
                logger.info(() -> "Successfully deleted user with ID: " + userId);
            } else {
                logger.warning(() -> "No rows affected when deleting user with ID: " + userId);
            }
            
            return deletionSuccessful;
            
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Database error while deleting user: " + userId, e);
            throw new DataAccessException("Failed to delete user", e);
        }
    }
    
    @Override
    public boolean usernameExists(String username) throws DataAccessException {
        try (PreparedStatement stmt = connection.prepareStatement(CHECK_USERNAME_EXISTS_SQL)) {
            stmt.setString(1, username);
            
            try (ResultSet resultSet = stmt.executeQuery()) {
                if (resultSet.next()) {
                    boolean exists = resultSet.getInt(1) > 0;
                    logger.fine(() -> "Username existence check for '" + username + "': " + exists);
                    return exists;
                }
            }
            
            return false;
            
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Database error while checking username existence: " + username, e);
            throw new DataAccessException("Failed to check username existence", e);
        }
    }
    
    @Override
    public boolean employeeHasAccount(String employeeId) throws DataAccessException {
        try (PreparedStatement stmt = connection.prepareStatement(CHECK_EMPLOYEE_HAS_ACCOUNT_SQL)) {
            // Convert String employeeId to int for database query
            int empId = Integer.parseInt(employeeId);
            stmt.setInt(1, empId);
            
            try (ResultSet resultSet = stmt.executeQuery()) {
                if (resultSet.next()) {
                    boolean hasAccount = resultSet.getInt(1) > 0;
                    logger.fine(() -> "Employee account check for ID '" + employeeId + "': " + hasAccount);
                    return hasAccount;
                }
            }
            
            return false;
            
        } catch (NumberFormatException e) {
            logger.log(Level.WARNING, "Invalid employee ID format: " + employeeId, e);
            throw new DataAccessException("Invalid employee ID format", e);
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Database error while checking employee account existence: " + employeeId, e);
            throw new DataAccessException("Failed to check employee account existence", e);
        }
    }
    
    @Override
    public int getUserCount() throws DataAccessException {
        try (PreparedStatement stmt = connection.prepareStatement(COUNT_USERS_SQL);
             ResultSet resultSet = stmt.executeQuery()) {
            
            if (resultSet.next()) {
                int count = resultSet.getInt(1);
                logger.fine(() -> "Total user count: " + count);
                return count;
            }
            
            return 0;
            
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Database error while counting users", e);
            throw new DataAccessException("Failed to count users", e);
        }
    }
    
    @Override
    public int getActiveUserCount() throws DataAccessException {
        try (PreparedStatement stmt = connection.prepareStatement(COUNT_ACTIVE_USERS_SQL);
             ResultSet resultSet = stmt.executeQuery()) {
            
            if (resultSet.next()) {
                int count = resultSet.getInt(1);
                logger.fine(() -> "Active user count: " + count);
                return count;
            }
            
            return 0;
            
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Database error while counting active users", e);
            throw new DataAccessException("Failed to count active users", e);
        }
    }
    
    @Override
    public int getInactiveUserCount() throws DataAccessException {
        try (PreparedStatement stmt = connection.prepareStatement(COUNT_INACTIVE_USERS_SQL);
             ResultSet resultSet = stmt.executeQuery()) {
            
            if (resultSet.next()) {
                int count = resultSet.getInt(1);
                logger.fine(() -> "Inactive user count: " + count);
                return count;
            }
            
            return 0;
            
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Database error while counting inactive users", e);
            throw new DataAccessException("Failed to count inactive users", e);
        }
    }
    
    @Override
    public int getUserCountByRole(int roleId) throws DataAccessException {
        try (PreparedStatement stmt = connection.prepareStatement(COUNT_USERS_BY_ROLE_SQL)) {
            stmt.setInt(1, roleId);
            
            try (ResultSet resultSet = stmt.executeQuery()) {
                if (resultSet.next()) {
                    int count = resultSet.getInt(1);
                    logger.fine(() -> "User count for role ID " + roleId + ": " + count);
                    return count;
                }
                
                return 0;
            }
            
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Database error while counting users by role: " + roleId, e);
            throw new DataAccessException("Failed to count users by role", e);
        }
    }
}
