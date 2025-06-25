/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.motorph.usermanagement.model;

import com.motorph.usermanagement.view.LoginPage;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * Represents a system user, complete with all the details needed
 * for database operations and login handling.
 * 
 * @author Harvey 
 */
public class User {
    // Basic user info
    protected int userId;                           // Primary key from DB
    protected int employeeId;                    // Links to employee record 
    protected String username;                  // Unique login name
    protected String passwordHashed;         // Stored as a hashed string (e.g., BCrypt)
    protected int roleId;                            // Role (e.g., admin, employee, etc.)
    
    // Account management fields
    protected Timestamp accountCreated;     // When account was created
    protected Timestamp lastLogin;             // Last successful login timestamp
    protected boolean isActive;                  // Account status flag
    
    // Used only at runtime
    protected boolean authenticationResult;  // Login attempt result
    
    /**
     * Default constructor useful when creating a blank user object
     * Typically used by DAO classes during object construction.
     */
    public User() {
        this.isActive = true; // By default, new users are active
        this.accountCreated = Timestamp.valueOf(LocalDateTime.now());
    }
    
    /**
     * Constructor used when loading user data from the database. 
     * Used primarily by DAO mapping operations.
     * 
     * @param userId Database primary key
     * @param username User's login name
     * @param passwordHashed BCrypt hashed password
     * @param employeeId Associated employee identifier - NOW INT
     * @param roleId User's role identifier
     * @param accountCreated Account creation timestamp
     * @param lastLogin Last login timestamp
     * @param isActive Account active status
     */
    public User(int userId, String username, String passwordHashed, int employeeId, 
                int roleId, Timestamp accountCreated, Timestamp lastLogin, boolean isActive) {
        this.userId = userId;
        this.username = username;
        this.passwordHashed = passwordHashed;
        this.employeeId = employeeId;
        this.roleId = roleId;
        this.accountCreated = accountCreated;
        this.lastLogin = lastLogin;
        this.isActive = isActive;
    }  
    
    /**
     * Constructor used when creating a new user manually 
     * Used during user registration or manual user creation.
     * 
     * @param username The user's login identifier
     * @param passwordHashed Pre-hashed password (should be BCrypt hashed)
     * @param employeeId Associated employee ID - NOW INT
     * @param roleId User's role classification
     */
    public User(String username, String passwordHashed, int employeeId, int roleId) {
        this();
        this.username = username;
        this.passwordHashed = passwordHashed;
        this.employeeId = employeeId;
        this.roleId = roleId;
    }

    /**
     * Updates the user's last login time to now.
     * Call this right after a successful login.
     */
    public void updateLastLogin() {
        this.lastLogin = Timestamp.valueOf(LocalDateTime.now());
    }
    
    /**
     * Checks if the user account is currently active and can log in.
     * Inactive accounts are blocked from system access.
     * 
     * @return true if account is active, false otherwise
     */
    public boolean canLogin() {
        return this.isActive;
    }
    
    /**
     * Deactivates the user account without deleting it.
     * Useful for temporarily suspending access while preserving audit trails.
     */
    public void deactivateAccount() {
        this.isActive = false;
    }
    
    /**
     * Reactivates a previously deactivated user account.
     */
    public void activateAccount() {
        this.isActive = true;
    }
    
    // ----- Getters -----
    
    /**
     * @return Database primary key for this user
     */
    public int getUserId() {
        return userId;
    }
    
    /**
     * @return User's login username
     */
    public String getUsername() {
        return username;
    }
    
    /**
     * @return Associated employee identifier - NOW RETURNS INT
     */
    public int getEmployeeId() {
        return employeeId;
    }
    
    /**
     * DEPRECATED: Use getEmployeeId() instead
     * Kept for backward compatibility with older code that expects a String.
     */
    @Deprecated
    public String getEmployeeID() {
        return String.valueOf(employeeId);
    }
    
    /**
     * @return BCrypt hashed password
     */
    public String getPasswordHashed() {
        return passwordHashed;
    }
    
    /**
     * @return User's role identifier
     */
    public int getRoleId() {
        return roleId;
    }
    
    /**
     * @return Timestamp when account was created
     */
    public Timestamp getAccountCreated() {
        return accountCreated;
    }
    
    /**
     * @return Last successful login timestamp
     */
    public Timestamp getLastLogin() {
        return lastLogin;
    }  
    
    /**
     * @return Whether account is currently active
     */
    public boolean isActive() {
        return isActive;
    }
    
    /**
     * @return Result of last authentication attempt
     */
    public boolean getAuthenticationResult() {
        return authenticationResult;
    }
    
    /**
     * Sets the account creation timestamp.
     * Used primarily by DAO classes when mapping database results.
     * 
     * @param accountCreated The timestamp when the account was created
     */
    public void setAccountCreated(Timestamp accountCreated) {
       this.accountCreated = accountCreated;
    }
    
    // ----- Setters -----
    
    public void setUserId(int userId) {
        this.userId = userId;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    
    public void setPasswordHashed(String passwordHashed) {
        this.passwordHashed = passwordHashed;
    }
    
    /**
     * @param employeeId Employee ID as integer 
     */
    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }
    
    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }
    
    public void setActive(boolean active) {
        this.isActive = active;
    }
    
    public void setAuthenticationResult(boolean authenticationResult) {
        this.authenticationResult = authenticationResult;
    }
    
    public void setLastLogin(Timestamp lastLogin) {
        this.lastLogin = lastLogin;
    }
    
     // ----- Utility methods -----
    
    /**
     * Adds a window listener to handle logout confirmation when user tries to close the window.
     * Prevents accidental application closure and ensures proper logout flow.
     * 
     * @param frame The JFrame to add logout behavior to
     */
    public static void addLogoutListener(JFrame frame) {
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                logout(frame);
            }
        });
    }
    
    /**
     * Handles user logout with confirmation dialog.
     * Closes current session and returns to login screen.
     * 
     * @param frame Current application frame to close
     */
    public static void logout(JFrame frame) {
        int confirmed = JOptionPane.showConfirmDialog(frame,
                "Are you sure you want to log out?", "Logout Confirmation",
                JOptionPane.YES_NO_OPTION);

        if (confirmed == JOptionPane.YES_OPTION) {
            frame.dispose(); // Close current frame cleanly
            new LoginPage().setVisible(true); // Return to login screen
        }
    }
    
    /**
     * Returns string representation of User for debugging purposes.
     * Excludes sensitive information like passwords.
     */
    @Override
    public String toString() {
        return String.format("User{userId=%d, username='%s', employeeId=%d, roleId=%d, isActive=%s}", 
                           userId, username, employeeId, roleId, isActive);
    }
}