/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.motorph.usermanagement.controller;

import com.motorph.usermanagement.model.User;
import com.motorph.usermanagement.model.Admin;
import com.motorph.usermanagement.model.NonAdmin;
import com.motorph.usermanagement.model.Access;
import com.motorph.usermanagement.service.UserService;
import com.motorph.usermanagement.service.UserServiceImpl;
import com.motorph.usermanagement.security.Authorizer;
import com.motorph.usermanagement.security.AuthorizerImpl;
import com.motorph.usermanagement.exception.InvalidCredentialsException;
import com.motorph.usermanagement.exception.DataAccessException;
import com.motorph.usermanagement.view.LoginPage;
import com.motorph.usermanagement.view.CompanyHomePage;
import com.motorph.employeemanagement.view.selfservice.ProfilePage;

import javax.swing.JOptionPane;
import javax.swing.JFrame;
import java.util.logging.Logger;
import java.util.logging.Level;

/**
 * @author Harvey 
 *
 * This controller class handles all login-related business logic from LoginPage and CompanyHomePage.
 * Separates the presentation layer from business logic and focuses solely on authentication
 * and dashboard navigation functionality.
 */
public class LoginController {
    private static final Logger logger = Logger.getLogger(LoginController.class.getName());
    
    private final UserService userService;
    private final LoginPage loginView;
    private final Authorizer authorizer;
    
    /**
     * Constructor initializes the controller with required services and view.
     */
    public LoginController(LoginPage loginView) {
        this.loginView = loginView;
        this.userService = new UserServiceImpl();
        this.authorizer = new AuthorizerImpl();
        
        logger.info("LoginController initialized successfully for login functionality");
    }
    
    /**
     * Main login handler - processes authentication and redirects user
     * 
     * @param username User's username
     * @param password User's password
     * @return LoginResult containing success status and any error messages
     */
    public LoginResult handleLogin(String username, String password) {
        try {
            // Validate input
            LoginResult validationResult = validateLoginInput(username, password);
            if (!validationResult.isSuccess()) {
                return validationResult;
            }
            
            // Authenticate user through simplified UserService
            User authenticatedUser = userService.authenticate(username.trim(), password);
            
            // Open appropriate dashboard based on user role
            openUserDashboard(authenticatedUser);
            
            logger.info(() -> "Successful login for user: " + username);
            return LoginResult.success("Login successful");
            
        } catch (InvalidCredentialsException e) {
            String errorMsg = "Invalid username or password. Please try again.";
            logger.warning(() -> "Failed login attempt for username: " + username + " - " + e.getMessage());
            return LoginResult.failure(errorMsg);
            
        } catch (DataAccessException e) {
            String errorMsg = "Unable to connect to the system. Please try again later.";
            logger.log(Level.SEVERE, "Database error during login for username: " + username, e);
            return LoginResult.failure(errorMsg);
            
        } catch (Exception e) {
            String errorMsg = "An unexpected error occurred. Please contact support.";
            logger.log(Level.SEVERE, "Unexpected error during login for username: " + username, e);
            return LoginResult.failure(errorMsg);
        }
    }
    
    /**
     * Validates user input for login
     */
    private LoginResult validateLoginInput(String username, String password) {
        if (username == null || username.trim().isEmpty()) {
            return LoginResult.failure("Please enter your username.");
        }
        
        if (password == null || password.isEmpty()) {
            return LoginResult.failure("Please enter your password.");
        }
        
        return LoginResult.success("Input valid");
    }
    
    /**
     * Opens the appropriate dashboard based on user role
     */
    private void openUserDashboard(User user) throws Exception {
        logger.info(() -> "Opening dashboard for user: " + user.getUsername() + 
                          ", Role ID: " + user.getRoleId() + 
                          ", Employee ID: " + user.getEmployeeId());
        
        // Use Authorizer to check roles
        boolean isAdmin = authorizer.isAdminRole(user);
        boolean isNonAdmin = authorizer.isNonAdminRole(user);
        
        logger.info(() -> String.format("Role check results for %s: Admin=%s, NonAdmin=%s", 
                                      user.getUsername(), isAdmin, isNonAdmin));
        
        try {
            if (isNonAdmin) {
                // Check NonAdmin BEFORE Admin to avoid conflicts
                logger.info(() -> "Opening NonAdmin dashboard for: " + user.getUsername());
                openNonAdminDashboard(user);
            } else if (isAdmin) {
                logger.info(() -> "Opening Admin dashboard for: " + user.getUsername());
                openAdminDashboard(user);
            } else {
                throw new Exception("Unknown user role: " + user.getRoleId() + " for user: " + user.getUsername());
            }
            
            logger.info(() -> "Dashboard opened successfully for user: " + user.getUsername());
            
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Failed to open dashboard for user: " + user.getUsername(), e);
            throw new Exception("Failed to open dashboard: " + getErrorMessage(e), e);
        }
    }
    
    /**
     * Open Admin dashboard 
     */
    private void openAdminDashboard(User user) throws Exception {
        logger.info(() -> "Opening admin dashboard for user: " + user.getUsername());
        
        // Create Admin object from authenticated user
        Admin admin = new Admin(user);
        
        // Open the CompanyHomePage (Admin Dashboard)
        CompanyHomePage adminDashboard = new CompanyHomePage(admin);
        adminDashboard.setVisible(true);
        
        logger.info(() -> "Admin dashboard opened for user: " + user.getUsername());
    }
    
    /**
     * Open NonAdmin dashboard - directs to ProfilePage
     */
    private void openNonAdminDashboard(User user) throws Exception {
        logger.info(() -> "Opening NonAdmin dashboard (ProfilePage) for user: " + user.getUsername());
        
        // Create NonAdmin object from authenticated user
        NonAdmin nonAdmin = new NonAdmin(user);
        
        // Open the ProfilePage (NonAdmin Dashboard)
        ProfilePage nonAdminDashboard = new ProfilePage(nonAdmin);
        nonAdminDashboard.setVisible(true);
        
        logger.info(() -> "NonAdmin dashboard (ProfilePage) opened for user: " + user.getUsername());
    }
    
    // ============= COMPANY HOME PAGE BUSINESS LOGIC =============
    // These methods handle navigation from CompanyHomePage to various modules
    
    /**
     * Handles Employee Information button click
     */
    public void handleEmployeeInformation(Admin admin, JFrame currentFrame) {
        try {
            logger.info(() -> "Handling Employee Information request for admin: " + admin.getUsername());
            
            if (admin == null) {
                showAccessError(currentFrame, "Admin session not initialized.");
                return;
            }
            
            // Use Access class to handle employee information
            Access.accessEmployeeInformation(admin);
            currentFrame.setVisible(false);
            
            logger.info(() -> "Employee Information accessed successfully for admin: " + admin.getUsername());
            
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error accessing Employee Information for admin: " + admin.getUsername(), e);
            showAccessError(currentFrame, "Failed to access Employee Information: " + e.getMessage());
        }
    }
    
    /**
     * Handles Employee Request button click
     */
    public void handleEmployeeRequest(Admin admin, JFrame currentFrame) {
        try {
            logger.info(() -> "Handling Employee Request for admin: " + admin.getUsername());
            
            if (admin == null) {
                showAccessError(currentFrame, "Admin session not initialized.");
                return;
            }
            
            // Use Access class to handle employee requests
            Access.accessEmployeeRequests(admin);
            currentFrame.setVisible(false);
            
            logger.info(() -> "Employee Request accessed successfully for admin: " + admin.getUsername());
            
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error accessing Employee Request for admin: " + admin.getUsername(), e);
            showAccessError(currentFrame, "Failed to access Employee Request: " + e.getMessage());
        }
    }
    
    /**
     * Handles Payroll button click
     */
    public void handlePayroll(Admin admin, JFrame currentFrame) {
        try {
            logger.info(() -> "Handling Payroll request for admin: " + admin.getUsername());
            
            if (admin == null) {
                showAccessError(currentFrame, "Admin session not initialized.");
                return;
            }
            
            // Use Access class to handle payroll
            Access.accessPayrollList(admin);
            currentFrame.setVisible(false);
            
            logger.info(() -> "Payroll accessed successfully for admin: " + admin.getUsername());
            
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error accessing Payroll for admin: " + admin.getUsername(), e);
            showAccessError(currentFrame, "Failed to access Payroll: " + e.getMessage());
        }
    }
    
    /**
     * Handles Attendance button click
     */
    public void handleAttendance(Admin admin, JFrame currentFrame) {
        try {
            logger.info(() -> "Handling Attendance request for admin: " + admin.getUsername());
            
            if (admin == null) {
                showAccessError(currentFrame, "Admin session not initialized.");
                return;
            }
            
            // Use Access class to handle attendance
            Access.accessAttendanceBiweekly(admin);
            currentFrame.setVisible(false);
            
            logger.info(() -> "Attendance accessed successfully for admin: " + admin.getUsername());
            
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error accessing Attendance for admin: " + admin.getUsername(), e);
            showAccessError(currentFrame, "Failed to access Attendance: " + e.getMessage());
        }
    }
    
    /**
     * Handles Self Service Portal button click
     */
    public void handleSelfServicePortal(Admin admin, JFrame currentFrame) {
        try {
            logger.info(() -> "Handling Self Service Portal request for admin: " + admin.getUsername());
            
            if (admin == null) {
                showAccessError(currentFrame, "Admin session not initialized.");
                return;
            }
            
            // Use Access class to handle self service portal
            Access.accessSelfServicePortal(admin);
            currentFrame.setVisible(false);
            
            logger.info(() -> "Self Service Portal accessed successfully for admin: " + admin.getUsername());
            
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error accessing Self Service Portal for admin: " + admin.getUsername(), e);
            showAccessError(currentFrame, "Failed to access Self Service Portal: " + e.getMessage());
        }
    }
    
    /**
     * Handles logout functionality
     */
    public void handleLogout(Admin admin, JFrame currentFrame) {
        try {
            logger.info(() -> "Handling logout for admin: " + (admin != null ? admin.getUsername() : "null"));
            
            // Close current frame
            currentFrame.dispose();
            
            // Open login page
            new LoginPage().setVisible(true);
            
            logger.info(() -> "Logout successful for admin: " + (admin != null ? admin.getUsername() : "null"));
            
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error during logout", e);
            
            // Fallback logout
            currentFrame.dispose();
            new LoginPage().setVisible(true);
        }
    }
    
    /**
     * Shows access error dialog
     */
    private void showAccessError(JFrame parent, String message) {
        JOptionPane.showMessageDialog(
            parent,
            "Error: " + message,
            "Access Error",
            JOptionPane.ERROR_MESSAGE
        );
    }
    
    /**
     * Gets error message from exception
     */
    private String getErrorMessage(Exception e) {
        String errorDetails = e.getMessage();
        return (errorDetails == null || errorDetails.trim().isEmpty()) ? 
               e.getClass().getSimpleName() : errorDetails;
    }
    
    /* ============= UI DIALOG METHODS =============
    /* These methods handle user interaction dialogs
    /*
     * Shows error dialog to user
     */
    public void showErrorDialog(String message) {
        JOptionPane.showMessageDialog(
            loginView, 
            message, 
            "Login Error", 
            JOptionPane.ERROR_MESSAGE
        );
    }
    
    /**
     * Shows warning dialog to user
     */
    public void showWarningDialog(String message) {
        JOptionPane.showMessageDialog(
            loginView, 
            message, 
            "Login Warning", 
            JOptionPane.WARNING_MESSAGE
        );
    }
    
    /**
     * Shows database error dialog with technical details
     */
    public void showDatabaseErrorDialog(String message) {
        JOptionPane.showMessageDialog(
            loginView, 
            "Unable to connect to the system. Please try again later.\n" +
            "Technical details: " + message, 
            "System Error", 
            JOptionPane.ERROR_MESSAGE
        );
    }
    
    /**
     * Shows dashboard error dialog
     */
    public void showDashboardErrorDialog(String username, String errorMessage) {
        JOptionPane.showMessageDialog(
            loginView, 
            "Login successful, but there was an error opening the dashboard.\n" +
            "Error: " + errorMessage + "\n" +
            "Please contact your system administrator.", 
            "Dashboard Error", 
            JOptionPane.ERROR_MESSAGE
        );
    }
    
    /**
     * Inner class to represent login operation results
     */
    public static class LoginResult {
        private final boolean success;
        private final String message;
        
        private LoginResult(boolean success, String message) {
            this.success = success;
            this.message = message;
        }
        
        public static LoginResult success(String message) {
            return new LoginResult(true, message);
        }
        
        public static LoginResult failure(String message) {
            return new LoginResult(false, message);
        }
        
        public boolean isSuccess() {
            return success;
        }
        
        public String getMessage() {
            return message;
        }
    }
}