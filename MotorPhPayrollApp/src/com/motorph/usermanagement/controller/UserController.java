/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.motorph.usermanagement.controller;

import com.motorph.usermanagement.model.User;
import com.motorph.usermanagement.service.UserService;
import com.motorph.usermanagement.service.UserServiceImpl;
import com.motorph.usermanagement.exception.UserNotFoundException;
import com.motorph.usermanagement.exception.DuplicateUserException;
import com.motorph.usermanagement.exception.DataAccessException;
import com.motorph.usermanagement.view.UserManagementPage;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;
import java.util.logging.Level;
/**
 * This manages CRUD operations for users and coordinates with the view layer.
 * 
 * @author Harvey 
 */
public class UserController {
    private static final Logger logger = Logger.getLogger(UserController.class.getName());
    
    private final UserService userService;
    private final UserManagementPage userView;
    
    /**
     * Constructor initializes the controller with required services and view
     * 
     * @param userView the screen or panel that shows user info
     */
    public UserController(UserManagementPage userView) {
        this.userView = userView;
        this.userService = new UserServiceImpl();
        
        logger.info("UserController initialized successfully");
    }
    
    /**
     * Loads all users and refreshes the view.
     */
    public void loadAllUsers() {
        try {
            List<User> users = userService.getAllUsers();
            updateUserTable(users);
            logger.info(() -> "Loaded " + users.size() + " users successfully");
            
        } catch (DataAccessException e) {
            showErrorMessage("Error loading users: " + e.getMessage());
            logger.log(Level.SEVERE, "Error loading users", e);
        }
    }
    
    /**
     * Loads only active users and refreshes the view.
     */
    public void loadActiveUsers() {
        try {
            List<User> activeUsers = userService.getActiveUsers();
            updateUserTable(activeUsers);
            logger.info(() -> "Loaded " + activeUsers.size() + " active users successfully");
            
        } catch (DataAccessException e) {
            showErrorMessage("Error loading active users: " + e.getMessage());
            logger.log(Level.SEVERE, "Error loading active users", e);
        }
    }
    
    /**
     * Creates a new user account with the provided information.
     * 
     * @param username the chosen username
     * @param password the user's password
     * @param employeeId the ID of the employee linked to this user
     * @param roleId the role assigned to the user
     */
    public void createUser(String username, String password, int employeeId, int roleId) {
        try {
            // Basic validation
            if (username == null || username.trim().isEmpty()) {
                showErrorMessage("Username is required");
                return;
            }
            
            if (password == null || password.isEmpty()) {
                showErrorMessage("Password is required");
                return;
            }
            
            if (employeeId <= 0) {
                showErrorMessage("Valid Employee ID is required");
                return;
            }
            
            // Create user object
            User newUser = new User();
            newUser.setUsername(username.trim());
            newUser.setPasswordHashed(password); 
            newUser.setEmployeeId(employeeId);   
            newUser.setRoleId(roleId);
            
            // Register the user
            int userId = userService.registerUser(newUser);
            
            if (userId > 0) {
                showSuccessMessage("User created successfully with ID: " + userId);
                loadAllUsers(); // Refresh the user list
                logger.info(() -> "Successfully created user: " + username + " with ID: " + userId);
            } else {
                showErrorMessage("Failed to create user. Please try again.");
            }
            
        } catch (DuplicateUserException e) {
            showErrorMessage("Username already exists. Please choose a different username.");
            logger.warning(() -> "Duplicate username attempt: " + username);
            
        } catch (DataAccessException e) {
            showErrorMessage("Database error: " + e.getMessage());
            logger.log(Level.SEVERE, "Database error creating user", e);
            
        } catch (Exception e) {
            showErrorMessage("Unexpected error occurred while creating user");
            logger.log(Level.SEVERE, "Unexpected error creating user", e);
        }
    }
    
    /**
     * Updates an existing user's information.
     * 
     * @param userId the ID of the user to update
     * @param username the new username
     * @param employeeId the updated employee ID
     * @param roleId the updated role
     */
    public void updateUser(int userId, String username, int employeeId, int roleId) {
        try {
            // Get existing user first
            Optional<User> existingUser = userService.getUserById(userId);
            if (!existingUser.isPresent()) {
                showErrorMessage("User not found");
                return;
            }
            
            User userToUpdate = existingUser.get();
            userToUpdate.setUsername(username.trim());
            userToUpdate.setEmployeeId(employeeId);  
            userToUpdate.setRoleId(roleId);
            
            boolean success = userService.updateUser(userToUpdate);
            
            if (success) {
                showSuccessMessage("User updated successfully");
                loadAllUsers(); // Refresh the user list
                logger.info(() -> "Successfully updated user ID: " + userId);
            } else {
                showErrorMessage("Failed to update user");
            }
            
        } catch (UserNotFoundException e) {
            showErrorMessage("User not found: " + e.getMessage());
            logger.warning(() -> "User not found for update: " + userId);
            
        } catch (DataAccessException e) {
            showErrorMessage("Database error: " + e.getMessage());
            logger.log(Level.SEVERE, "Database error updating user", e);
        }
    }
    
    /**
     * Activates or deactivates a user account.
     * 
     * @param userId the user's ID
     * @param isActive true to activate, false to deactivate
     */
    public void setUserStatus(int userId, boolean isActive) {
        try {
            boolean success = userService.setUserStatus(userId, isActive);
            
            if (success) {
                String statusText = isActive ? "activated" : "deactivated";
                showSuccessMessage("User " + statusText + " successfully");
                loadAllUsers(); // Refresh the user list
                logger.info(() -> "Successfully " + statusText + " user ID: " + userId);
            } else {
                showErrorMessage("Failed to change user status");
            }
            
        } catch (UserNotFoundException e) {
            showErrorMessage("User not found: " + e.getMessage());
            logger.warning(() -> "User not found for status change: " + userId);
            
        } catch (DataAccessException e) {
            showErrorMessage("Database error: " + e.getMessage());
            logger.log(Level.SEVERE, "Database error changing user status", e);
        }
    }
    
    /**
     * Searches for users by username.
     * 
     * @param searchTerm the text to search for
     */
    public void searchUsers(String searchTerm) {
        try {
            if (searchTerm == null || searchTerm.trim().isEmpty()) {
                loadAllUsers(); // Load all users if search term is empty
                return;
            }
            
            List<User> searchResults = userService.searchUsersByUsername(searchTerm.trim());
            updateUserTable(searchResults);
            
            logger.info(() -> "Search completed for term: " + searchTerm + ", found " + searchResults.size() + " results");
            
        } catch (DataAccessException e) {
            showErrorMessage("Error searching users: " + e.getMessage());
            logger.log(Level.SEVERE, "Error searching users", e);
        }
    }
    
    /**
     * Resets a user's password.
     * 
     * @param userId the ID of the user
     * @param newPassword the new password to set
     */
    public void resetUserPassword(int userId, String newPassword) {
        try {
            if (newPassword == null || newPassword.isEmpty()) {
                showErrorMessage("New password cannot be empty");
                return;
            }
            
            boolean success = userService.resetPassword(userId, newPassword);
            
            if (success) {
                showSuccessMessage("Password reset successfully");
                logger.info(() -> "Password reset for user ID: " + userId);
            } else {
                showErrorMessage("Failed to reset password");
            }
            
        } catch (UserNotFoundException e) {
            showErrorMessage("User not found: " + e.getMessage());
            logger.warning(() -> "User not found for password reset: " + userId);
            
        } catch (DataAccessException e) {
            showErrorMessage("Database error: " + e.getMessage());
            logger.log(Level.SEVERE, "Database error resetting password", e);
        }
    }
    
    /**
     * Updates the user table in the view with the provided user list.
     */
    private void updateUserTable(List<User> users) {
        DefaultTableModel tableModel = new DefaultTableModel();
        
        // Set column headers
        String[] columnNames = {"User ID", "Username", "Employee ID", "Role ID", "Status", "Created Date", "Last Login"};
        tableModel.setColumnIdentifiers(columnNames);
        
        // Add user data to table model
        for (User user : users) {
            Object[] rowData = {
                user.getUserId(),
                user.getUsername(),
                user.getEmployeeId(),        
                user.getRoleId(),            
                user.isActive() ? "Active" : "Inactive",
                user.getAccountCreated(),
                user.getLastLogin()
            };
            tableModel.addRow(rowData);
        }
        
        // Update the view's table
        userView.setTableModel(tableModel);
    }
    
    /**
     * Shows error message to user.
     */
    private void showErrorMessage(String message) {
        JOptionPane.showMessageDialog(
            userView, 
            message, 
            "Error", 
            JOptionPane.ERROR_MESSAGE
        );
    }
    
    /**
     * Shows success message to user.
     */
    private void showSuccessMessage(String message) {
        JOptionPane.showMessageDialog(
            userView, 
            message, 
            "Success", 
            JOptionPane.INFORMATION_MESSAGE
        );
    }
    
    /**
     * Gets total user count for display purposes.
     */
    public void updateUserCount() {
        try {
            int totalUsers = userService.getUserCount();
            int activeUsers = userService.getActiveUserCount();
            
            userView.updateUserCountDisplay(totalUsers, activeUsers);
            
        } catch (DataAccessException e) {
            logger.log(Level.WARNING, "Error getting user count", e);
        }
    }
}
