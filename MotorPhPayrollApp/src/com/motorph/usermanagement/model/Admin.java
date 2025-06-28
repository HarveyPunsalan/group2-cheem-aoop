/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.motorph.usermanagement.model;

/** 
 * Represents an Admin user in the system.
 * Extends User class and provides admin-specific functionality
 * 
 * @author Harvey
 */
public class Admin extends User {
    // Additional fields for role information (not stored in User base class)
    private String roleName;
    private String roleDescription;
    
    public Admin() {
        super();
    }
    
    /**
     * Constructor for creating Admin with basic credentials.
     * @param username The user's login identifier
     * @param passwordHashed Pre-hashed password (should be BCrypt hashed)
     * @param employeeId Associated employee ID
     * @param roleId User's role classification
     */
    public Admin(String username, String passwordHashed, int employeeId, int roleId) {
        super(username, passwordHashed, employeeId, roleId);
    }
    
    /**
     * Constructor for creating Admin from database result set data.
     * @param userId Database primary key
     * @param username User's login name
     * @param passwordHashed BCrypt hashed password
     * @param employeeId Associated employee identifier
     * @param roleId User's role identifier
     * @param accountCreated Account creation timestamp
     * @param lastLogin Last login timestamp
     * @param isActive Account active status
     */
    public Admin(int userId, String username, String passwordHashed, int employeeId, 
                 int roleId, java.sql.Timestamp accountCreated, java.sql.Timestamp lastLogin, boolean isActive) {
        super(userId, username, passwordHashed, employeeId, roleId, accountCreated, lastLogin, isActive);
    }
    
    /**
     * Copy constructor to create Admin from existing User object.
     * @param user Existing User object to copy from
     */
    public Admin(User user) {
        super(user.getUserId(), user.getUsername(), user.getPasswordHashed(), 
              user.getEmployeeId(), user.getRoleId(), user.getAccountCreated(), 
              user.getLastLogin(), user.isActive());
    }
    
    /**
     * Static method to handle admin login via Access class
     * Maintains compatibility with existing UI code
     */
    public static void login(User admin) {
        Access.accessCompanyHomePage((Admin) admin);
    }
    
    /**
     * Determines if this user is an admin.
     * Since this is the Admin class, it always returns true.
     * @return true for Admin instances
     */
    public boolean isAdmin() {
        return true;
    }
    
    // Getters and setters for role information
    public String getRoleName() {
        return roleName;
    }
    
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
    
    public String getRoleDescription() {
        return roleDescription;
    }
    
    public void setRoleDescription(String roleDescription) {
        this.roleDescription = roleDescription;
    }
}