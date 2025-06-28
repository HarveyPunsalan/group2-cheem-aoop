/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.motorph.usermanagement.util;

/**
 * Contains constant values used throughout the user management system
 * This includes database table names, default values, and configuration settings
 * 
 * @author harvey punsalan
 */
public final class Constants {
    // Private constructor to prevent instantiation
    private Constants() {
        throw new IllegalStateException("Constants class should not be instantiated");
    }
    
    // Database table names
    public static final String TABLE_SYSTEM_USER = "system_user";
    public static final String TABLE_USER_ROLE = "user_role";
    public static final String TABLE_USER_ACCESS = "user_access";
    public static final String TABLE_ROLE_ACCESS = "role_access";
    public static final String TABLE_ACCESS_CATEGORY = "access_category";
    public static final String TABLE_USER_ACTION = "user_action";
    public static final String TABLE_USER_RESOURCE = "user_resource";
    
    // Default values
    public static final int DEFAULT_PASSWORD_MIN_LENGTH = 8;
    public static final int DEFAULT_USERNAME_MIN_LENGTH = 3;
    public static final int DEFAULT_USERNAME_MAX_LENGTH = 20;
    public static final int DEFAULT_ROLE_NAME_MAX_LENGTH = 30;
    public static final int DEFAULT_ACCESS_NAME_MAX_LENGTH = 100;
    
    // User status values
    public static final boolean USER_ACTIVE = true;
    public static final boolean USER_INACTIVE = false;
    
    // Permission values
    public static final int PERMISSION_REQUIRES_APPROVAL = 1;
    public static final int PERMISSION_NO_APPROVAL = 0;
    
    // Default role IDs (these should match your database setup)
    public static final int ROLE_ADMIN = 1;
    public static final int ROLE_HR_MANAGER = 2;
    public static final int ROLE_PAYROLL_OFFICER = 3;
    public static final int ROLE_EMPLOYEE = 4;
    
    // Error messages
    public static final String ERROR_INVALID_USERNAME = "Username must be 3-20 characters and contain only letters, numbers, and underscores";
    public static final String ERROR_INVALID_PASSWORD = "Password must be at least 8 characters with letters and numbers";
    public static final String ERROR_INVALID_EMPLOYEE_ID = "Employee ID must be numeric and up to 10 digits";
    public static final String ERROR_DATABASE_CONNECTION = "Unable to connect to the database";
    public static final String ERROR_USER_NOT_FOUND = "User not found in the system";
    public static final String ERROR_DUPLICATE_USERNAME = "Username already exists";
    public static final String ERROR_INSUFFICIENT_PERMISSIONS = "Insufficient permissions for this operation";
    
    // Success messages
    public static final String SUCCESS_USER_CREATED = "User account created successfully";
    public static final String SUCCESS_USER_UPDATED = "User information updated successfully";
    public static final String SUCCESS_USER_DELETED = "User account deleted successfully";
    public static final String SUCCESS_PASSWORD_RESET = "Password reset successfully";
    public static final String SUCCESS_ROLE_ASSIGNED = "Role assigned successfully";
    
    // System configuration
    public static final int MAX_LOGIN_ATTEMPTS = 5;
    public static final int PASSWORD_EXPIRY_DAYS = 90;
    public static final int ACCOUNT_LOCKOUT_DURATION_MINUTES = 30;
    public static final int SESSION_TIMEOUT_MINUTES = 60;
    
    // Logging categories
    public static final String LOG_CATEGORY_AUTHENTICATION = "AUTHENTICATION";
    public static final String LOG_CATEGORY_USER_MANAGEMENT = "USER_MANAGEMENT";
    public static final String LOG_CATEGORY_ROLE_MANAGEMENT = "ROLE_MANAGEMENT";
    public static final String LOG_CATEGORY_PERMISSION_MANAGEMENT = "PERMISSION_MANAGEMENT";
    public static final String LOG_CATEGORY_DATABASE = "DATABASE";
    
    // Access categories (should match your database)
    public static final String ACCESS_CATEGORY_PAYROLL = "Payroll";
    public static final String ACCESS_CATEGORY_HR = "Human Resources";
    public static final String ACCESS_CATEGORY_ATTENDANCE = "Attendance";
    public static final String ACCESS_CATEGORY_EMPLOYEE_MANAGEMENT = "Employee Management";
    public static final String ACCESS_CATEGORY_USER_MANAGEMENT = "User Management";
    public static final String ACCESS_CATEGORY_REPORTS = "Reports";
    
    // Common actions
    public static final String ACTION_CREATE = "CREATE";
    public static final String ACTION_READ = "READ";
    public static final String ACTION_UPDATE = "UPDATE";
    public static final String ACTION_DELETE = "DELETE";
    public static final String ACTION_APPROVE = "APPROVE";
    public static final String ACTION_REJECT = "REJECT";
    
    // Time constants (in milliseconds)
    public static final long ONE_MINUTE_MS = 60 * 1000L;
    public static final long ONE_HOUR_MS = 60 * ONE_MINUTE_MS;
    public static final long ONE_DAY_MS = 24 * ONE_HOUR_MS;
}
