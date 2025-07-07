/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.motorph.usermanagement.controller;

import com.motorph.usermanagement.model.User;
import com.motorph.usermanagement.model.Admin;
import com.motorph.usermanagement.model.NonAdmin;
import com.motorph.usermanagement.service.UserService;
import com.motorph.usermanagement.service.UserServiceImpl;
import com.motorph.usermanagement.exception.InvalidCredentialsException;
import com.motorph.usermanagement.exception.DataAccessException;
import com.motorph.usermanagement.view.LoginPage;
import com.motorph.usermanagement.view.CompanyHomePage;
import com.motorph.employeemanagement.view.selfservice.ProfilePage;

import javax.swing.JFrame;       //
import javax.swing.JOptionPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Logger;
import java.util.logging.Level;
import java.lang.reflect.Field;
/**
 * @author Harvey 
 *
 * This controller class is for handling login-related operations.
 * Manages the interaction between login view and user services.
 */
public class LoginController {
    private static final Logger logger = Logger.getLogger(LoginController.class.getName());
    
    private final UserService userService;
    private final LoginPage loginView;
    
    /**
     * Constructor initializes the controller with required services and view.
     */
    public LoginController(LoginPage loginView) {
        this.loginView = loginView;
        this.userService = new UserServiceImpl();
        
        // Set up event listeners for the login view
        setupEventListeners();
        
        logger.info("LoginController initialized successfully");
    }
    
    /**
     * Sets up event listeners for login view components.
     */
    private void setupEventListeners() {
        // Note: LoginPage doesn't have setLoginButtonListener method
        // The login button logic is handled directly in LoginPage's jButton1LogInActionPerformed
        // If you want to use this controller, you'll need to modify LoginPage to accept a controller
        // For now, this method serves as a placeholder for future implementation
    }
    
    /**
     * Handles the login process when user clicks login button.
     * 
     * @param username User's username
     * @param password User's password
     */
    public void handleLogin(String username, String password) {
        try {
            // Validate input
            if (username == null || username.trim().isEmpty()) {
                showErrorMessage("Please enter a username");
                return;
            }
            
            if (password == null || password.isEmpty()) {
                showErrorMessage("Please enter a password");
                return;
            }
            
            // Attempt authentication
            User authenticatedUser = userService.authenticate(username.trim(), password);
            
            // Successful authentication - redirect based on role
            redirectUserBasedOnRole(authenticatedUser);
            
            // Close login window
            loginView.dispose();
            
            logger.info(() -> "Successful login for user: " + username);
            
        } catch (InvalidCredentialsException e) {
            showErrorMessage("Invalid username or password. Please try again.");
            logger.warning(() -> "Failed login attempt for username: " + username + " - " + e.getMessage());
            
        } catch (DataAccessException e) {
            showErrorMessage("System error occurred. Please try again later.");
            logger.log(Level.SEVERE, "Database error during login for username: " + username, e);
            
        } catch (Exception e) {
            showErrorMessage("An unexpected error occurred. Please contact support.");
            logger.log(Level.SEVERE, "Unexpected error during login for username: " + username, e);
        }
    }
    
    /**
     * Redirects user to appropriate page based on their role.
     */
    private void redirectUserBasedOnRole(User user) {
        try {
            // Determine if user is admin based on role ID
            boolean isAdmin = isUserAdmin(user);
            
            if (isAdmin) {
                // Create admin user using the correct constructor
                // Fixed: Using the 4-parameter constructor that exists in Admin class
                Admin admin = new Admin(user.getUsername(), user.getPasswordHashed(), 
                                      user.getEmployeeId(), user.getRoleId());
                
                CompanyHomePage homePage = new CompanyHomePage(admin);
                homePage.setVisible(true);
                
            } else {
                // Create non-admin user using the correct constructor
                // Fixed: Using the 4-parameter constructor that exists in NonAdmin class
                NonAdmin nonAdmin = new NonAdmin(user.getUsername(), user.getPasswordHashed(), 
                                               user.getEmployeeId(), user.getRoleId());
                
                ProfilePage profilePage = new ProfilePage(nonAdmin);
                profilePage.setVisible(true);
            }
            
        } catch (Exception e) {
            showErrorMessage("Error opening user dashboard. Please try again.");
            logger.log(Level.SEVERE, "Error redirecting user after login", e);
        }
    }
    
    /**
     * Determines if a user has admin privileges based on role ID.
     */
    private boolean isUserAdmin(User user) {
        // Assuming role ID 1 is for Admin, 2 for HR Admin, etc.
        // Adjust this logic based on your role structure
        int roleId = user.getRoleId();
        
        try {
            return roleId == 1 || roleId == 2; // Admin or HR Admin roles
        } catch (Exception e) {
            logger.warning(() -> "Error checking role ID: " + roleId + " for user: " + user.getUsername());
            return false;
        }
    }
    
    /**
     * Shows error message to user.
     */
    private void showErrorMessage(String message) {
        JOptionPane.showMessageDialog(
            loginView, 
            message, 
            "Login Error", 
            JOptionPane.ERROR_MESSAGE
        );
    }
    
    /**
     * Shows success message to user.
     */
    private void showSuccessMessage(String message) {
        JOptionPane.showMessageDialog(
            loginView, 
            message, 
            "Login Success", 
            JOptionPane.INFORMATION_MESSAGE
        );
    }
    
    /**
     * Gets username from login view using reflection to access private field.
     * This method provides a bridge to access LoginPage's text field.
     */
    public String getUsernameFromView() {
        try {
            // Use reflection to access the private field
            Field usernameField = LoginPage.class.getDeclaredField("jTextField1Username");
            usernameField.setAccessible(true);
            javax.swing.JTextField textField = (javax.swing.JTextField) usernameField.get(loginView);
            return textField != null ? textField.getText() : "";
        } catch (Exception e) {
            logger.log(Level.WARNING, "Could not access username field", e);
            return "";
        }
    }
    
    /**
     * Gets password from login view using reflection to access private field.
     * This method provides a bridge to access LoginPage's password field.
     */
    public String getPasswordFromView() {
        try {
            // Use reflection to access the private field
            Field passwordField = LoginPage.class.getDeclaredField("jPasswordFieldPassword");
            passwordField.setAccessible(true);
            javax.swing.JPasswordField passField = (javax.swing.JPasswordField) passwordField.get(loginView);
            return passField != null ? new String(passField.getPassword()) : "";
        } catch (Exception e) {
            logger.log(Level.WARNING, "Could not access password field", e);
            return "";
        }
    }
    
    /**
     * Inner class for handling login button click events.
     * This is currently not used since LoginPage handles its own button events.
     * To use this controller pattern properly, LoginPage would need to be modified.
     */
    private class LoginButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String username = getUsernameFromView();
            String password = getPasswordFromView();
            handleLogin(username, password);
        }
    }
}
