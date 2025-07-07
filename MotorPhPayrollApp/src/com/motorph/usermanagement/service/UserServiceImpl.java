/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.motorph.usermanagement.service;

import com.motorph.usermanagement.model.User;
import com.motorph.usermanagement.dao.UserDAO;
import com.motorph.usermanagement.dao.UserDAOImpl;
import com.motorph.usermanagement.dao.RoleDAO;
import com.motorph.usermanagement.dao.RoleDAOImpl;
import com.motorph.usermanagement.exception.UserNotFoundException;
import com.motorph.usermanagement.exception.DuplicateUserException;
import com.motorph.usermanagement.exception.InvalidCredentialsException;
import com.motorph.usermanagement.exception.DataAccessException;
import com.motorph.usermanagement.util.ValidationUtils;
import com.motorph.usermanagement.security.Authenticator;
import com.motorph.usermanagement.security.AuthenticatorImpl;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;
import java.util.logging.Level;
import java.util.stream.Collectors;

/**
 * Contains business logic for user management operations.
 * Coordinates between DAOs and applies business rules.
 * 
 * @author Harvey 
 */
public class UserServiceImpl implements UserService {
    private static final Logger logger = Logger.getLogger(UserServiceImpl.class.getName());
    
    private final UserDAO userDAO;
    private final RoleDAO roleDAO;
    private final Authenticator authenticator;
    
    /**
     * Constructor initializes required DAOs and services.
     */
    public UserServiceImpl() {
        this.userDAO = new UserDAOImpl();
        this.roleDAO = new RoleDAOImpl();
        this.authenticator = new AuthenticatorImpl();
        logger.info("UserServiceImpl initialized successfully");
    }
    
    /**
     * Constructor useful for testing
     * 
     * @param userDAO provides access to user-related data
     * @param roleDAO provides access to role-related data
     * @param authenticator handles authentication logic
     */
    public UserServiceImpl(UserDAO userDAO, RoleDAO roleDAO, Authenticator authenticator) {
        this.userDAO = userDAO;
        this.roleDAO = roleDAO;
        this.authenticator = authenticator;
    }
    
    @Override
    public int registerUser(User user) throws DuplicateUserException, DataAccessException {
        // Validate user input data
        if (!ValidationUtils.isValidUsername(user.getUsername())) {
            throw new IllegalArgumentException("Invalid username format. Username must be 3-50 characters and contain only letters, numbers, and underscores.");
        }
        
        // Check if username already exists
        if (userDAO.usernameExists(user.getUsername())) {
            throw new DuplicateUserException("Username '" + user.getUsername() + "' is already taken");
        }
        
        // Set default values for new user
        user.setActive(true);
        
        // Create the user record
        int userId = userDAO.createUser(user);
        logger.info(() -> "Successfully registered new user with ID: " + userId + ", username: " + user.getUsername());
        
        return userId;
    }
    
    @Override
     public User authenticate(String username, String password) throws InvalidCredentialsException, DataAccessException {
      try {
        // Input validation
        if (username == null || username.trim().isEmpty()) {
            throw new InvalidCredentialsException("Username cannot be empty");
        }
        
        if (password == null || password.isEmpty()) {
            throw new InvalidCredentialsException("Password cannot be empty");
        }
        
        AuthenticatorImpl authenticator = new AuthenticatorImpl();
        
        // Use the new authenticate method that handles migration
        boolean isAuthenticated = authenticator.authenticate(username.trim(), password);
        
        if (!isAuthenticated) {
            throw new InvalidCredentialsException("Invalid username or password");
        }
        
        // Retrieve the user details after successful authentication
        Optional<User> userOpt = userDAO.findByUsername(username.trim());
        
        if (!userOpt.isPresent()) {
            throw new InvalidCredentialsException("User not found after authentication");
        }
        
        User user = userOpt.get();
        
        // Check if user is active
        if (!user.isActive()) {
            throw new InvalidCredentialsException("User account is deactivated");
        }
        
        logger.info(() -> "Successful authentication for user: " + username);
        return user;
        
    } catch (InvalidCredentialsException e) {
        logger.warning(() -> "Authentication failed for user: " + username + " - " + e.getMessage());
        throw e;
    } catch (Exception e) {
        logger.log(Level.SEVERE, "Authentication error for user: " + username, e);
        throw new DataAccessException("Authentication failed due to system error", e);
    }
}
    
    @Override
    public boolean assignRole(int userId, int roleId) throws UserNotFoundException, DataAccessException {
        // Verify user exists
        Optional<User> userOptional = userDAO.findById(userId);
        if (!userOptional.isPresent()) {
            throw new UserNotFoundException("User with ID " + userId + " not found");
        }
        
        // Verify role exists
        if (!roleDAO.findById(roleId).isPresent()) {
            throw new IllegalArgumentException("Role with ID " + roleId + " not found");
        }
        
        // Update user's role directly (since assignRole method doesn't exist in DAO)
        User user = userOptional.get();
        user.setRoleId(roleId);
        boolean success = userDAO.updateUser(user);
        
        if (success) {
            logger.info(() -> "Successfully assigned role " + roleId + " to user " + userId);
        } else {
            logger.warning(() -> "Failed to assign role " + roleId + " to user " + userId);
        }
        
        return success;
    }
    
    @Override
    public boolean updateUser(User user) throws UserNotFoundException, DataAccessException {
        // Verify user exists
        if (!userDAO.findById(user.getUserId()).isPresent()) {
            throw new UserNotFoundException("User with ID " + user.getUserId() + " not found");
        }
        
        // Validate updated data
        if (!ValidationUtils.isValidUsername(user.getUsername())) {
            throw new IllegalArgumentException("Invalid username format");
        }
        
        boolean success = userDAO.updateUser(user);
        
        if (success) {
            logger.info(() -> "Successfully updated user with ID: " + user.getUserId());
        }
        
        return success;
    }
    
    @Override
    public Optional<User> getUserById(int userId) throws DataAccessException {
        return userDAO.findById(userId);
    }
    
    @Override
    public Optional<User> getUserByUsername(String username) throws DataAccessException {
        if (username == null || username.trim().isEmpty()) {
            return Optional.empty();
        }
        return userDAO.findByUsername(username.trim());
    }
    
    @Override
    public Optional<User> getUserByEmployeeId(int employeeId) throws DataAccessException {
        return userDAO.findByEmployeeId(String.valueOf(employeeId));
    }
    
    @Override
    public List<User> getAllUsers() throws DataAccessException {
        return userDAO.findAll();
    }
    
    @Override
    public List<User> getUsersByRole(int roleId) throws DataAccessException {
        return userDAO.findByRole(roleId);
    }
    
    @Override
    public List<User> getActiveUsers() throws DataAccessException {
        return userDAO.findActiveUsers();
    }
    
    @Override
    public boolean setUserStatus(int userId, boolean isActive) throws UserNotFoundException, DataAccessException {
        // Verify user exists
        if (!userDAO.findById(userId).isPresent()) {
            throw new UserNotFoundException("User with ID " + userId + " not found");
        }
        
        // Use the appropriate DAO method based on status
        boolean success;
        if (isActive) {
            success = userDAO.activateUser(userId);
        } else {
            success = userDAO.deactivateUser(userId);
        }
        
        String statusText = isActive ? "activated" : "deactivated";
        if (success) {
            logger.info(() -> "Successfully " + statusText + " user with ID: " + userId);
        }
        
        return success;
    }
    
    @Override
    public boolean changePassword(int userId, String newPassword) throws UserNotFoundException, DataAccessException {
        // Verify user exists
        if (!userDAO.findById(userId).isPresent()) {
            throw new UserNotFoundException("User with ID " + userId + " not found");
        }
        
        // Validate new password
        if (!ValidationUtils.isValidPassword(newPassword)) {
            throw new IllegalArgumentException("New password does not meet security requirements");
        }
        
        // Hash the new password
        String hashedPassword = authenticator.hashPassword(newPassword);
        
        // Use the updatePassword method from DAO interface
        boolean success = userDAO.updatePassword(userId, hashedPassword);
        
        if (success) {
            logger.info(() -> "Successfully changed password for user ID: " + userId);
        }
        
        return success;
    }
    
    @Override
    public boolean isUsernameAvailable(String username) throws DataAccessException {
        if (username == null || username.trim().isEmpty()) {
            return false;
        }
        return !userDAO.usernameExists(username.trim());
    }
    
    @Override
    public int getUserCount() throws DataAccessException {
        return userDAO.getUserCount();
    }
    
    @Override
    public int getActiveUserCount() throws DataAccessException {
        return userDAO.getActiveUserCount();
    }
    
    /**
     * Searches for users by username using partial matching.
     * Called by UserController.searchUsers() method.
     * 
     * @param searchTerm Partial username to search for
     * @return List of User objects matching the search criteria
     * @throws DataAccessException if database operation fails
     */
    @Override
    public List<User> searchUsersByUsername(String searchTerm) throws DataAccessException {
        if (searchTerm == null || searchTerm.trim().isEmpty()) {
            return getAllUsers(); // Return all users if search term is empty
        }
        
        // Get all users and filter by username containing the search term
        List<User> allUsers = userDAO.findAll();
        String lowerSearchTerm = searchTerm.trim().toLowerCase();
        
        return allUsers.stream()
                .filter(user -> user.getUsername().toLowerCase().contains(lowerSearchTerm))
                .collect(Collectors.toList());
    }
    
    /**
     * Resets a user's password to a new value.
     * Called by UserController.resetUserPassword() method.
     * 
     * @param userId User's database ID
     * @param newPassword New password to set (will be hashed in implementation)
     * @return true if password reset successful
     * @throws UserNotFoundException if user doesn't exist
     * @throws DataAccessException if database operation fails
     */
    @Override
    public boolean resetPassword(int userId, String newPassword) throws UserNotFoundException, DataAccessException {
        // Verify user exists
        if (!userDAO.findById(userId).isPresent()) {
            throw new UserNotFoundException("User with ID " + userId + " not found");
        }
        
        // Validate new password
        if (newPassword == null || newPassword.trim().isEmpty()) {
            throw new IllegalArgumentException("New password cannot be empty");
        }
        
        // For password reset, we might want less strict validation
        // But still ensure it meets minimum requirements
        if (newPassword.length() < 6) {
            throw new IllegalArgumentException("Password must be at least 6 characters long");
        }
        
        // Hash the new password
        String hashedPassword = authenticator.hashPassword(newPassword);
        
        // Use the updatePassword method from DAO interface
        boolean success = userDAO.updatePassword(userId, hashedPassword);
        
        if (success) {
            logger.info(() -> "Successfully reset password for user ID: " + userId);
        }
        
        return success;
    }
}
