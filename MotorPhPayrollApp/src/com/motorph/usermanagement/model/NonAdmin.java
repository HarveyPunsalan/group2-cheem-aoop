/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.motorph.usermanagement.model;

/**
 * @author Harvey 
 * 
 * Represents a NonAdmin user in the system.
 * This extends User class and provides non-admin user functionality
 */
public class NonAdmin extends User {
    
    public NonAdmin() {
        super();
    }
    
    /**
     * Constructor for creating NonAdmin with basic credentials.
     * @param username The user's login identifier
     * @param passwordHashed Pre-hashed password (should be BCrypt hashed)
     * @param employeeId Associated employee ID
     * @param roleId User's role classification
     */
    public NonAdmin(String username, String passwordHashed, int employeeId, int roleId) {
        super(username, passwordHashed, employeeId, roleId);
    }
    
    /**
     * Constructor for creating NonAdmin from database result set data.
     * @param userId Database primary key
     * @param username User's login name
     * @param passwordHashed BCrypt hashed password
     * @param employeeId Associated employee identifier
     * @param roleId User's role identifier
     * @param accountCreated Account creation timestamp
     * @param lastLogin Last login timestamp
     * @param isActive Account active status
     */
    public NonAdmin(int userId, String username, String passwordHashed, int employeeId, 
                    int roleId, java.sql.Timestamp accountCreated, java.sql.Timestamp lastLogin, boolean isActive) {
        super(userId, username, passwordHashed, employeeId, roleId, accountCreated, lastLogin, isActive);
    }
    
    /**
     * Copy constructor to create NonAdmin from existing User object.
     * @param user Existing User object to copy from
     */
    public NonAdmin(User user) {
        super(user.getUserId(), user.getUsername(), user.getPasswordHashed(), 
              user.getEmployeeId(), user.getRoleId(), user.getAccountCreated(), 
              user.getLastLogin(), user.isActive());
    }
    
    /**
     * Static method to handle non-admin login via Access class
     * Now properly routes NonAdmin users to their appropriate landing page
     * 
     * @param user The non-admin user attempting to log in
     */ 
    public static void login(User user) {
        if (user instanceof NonAdmin) {
            // Use the specific NonAdmin landing page method
            Access.accessNonAdminLandingPage((NonAdmin) user);
        } else {
            // Use the generic user landing page routing
            Access.accessUserLandingPage(user);
        }
    }
    
    /**
     * Instance method to access the NonAdmin landing page
     * Provides direct access for NonAdmin instances
     */
    public void accessLandingPage() {
        Access.accessNonAdminLandingPage(this);
    }
    
    /**
     * Determines if this user is an admin.
     * Since this is the NonAdmin class, it always returns false.
     * @return false for NonAdmin instances
     */
    public boolean isAdmin() {
        return false;
    }
}